/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.PedidoDAO;
import br.com.siscultbook.util.Utilitario;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ExibrRelatoriosEntreDatas implements InterfaceCommand {

    private PedidoDAO pedidoDAO;

    public ExibrRelatoriosEntreDatas(PedidoDAO pedidoDAO) {
        super();
        this.pedidoDAO = pedidoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {



        Pattern pData = Pattern.compile("^[0-9]{2}/[0-9]{2}/[0-9]{4}$");

        boolean retorno = false;
        String mensagem = "";
        GregorianCalendar dataI = null;
        GregorianCalendar dataFim = null;

        try {

            String dataInicial = request.getParameter("calendarioinicial");
            Matcher mDataInicial = pData.matcher(dataInicial);
            if (dataInicial.equals("") || dataInicial == null || !mDataInicial.find()
                    || Utilitario.verificaData(dataInicial) == null) {
                mensagem = mensagem + "Digite uma data válida, para o campo nascimento.<br />";
                retorno = true;
            } else {
                dataI = Utilitario.verificaData(dataInicial);
            }

            String dataFinal = request.getParameter("calendariofinal");
            Matcher mDataFinal = pData.matcher(dataInicial);
            if (dataFinal.equals("") || dataFinal == null || !mDataFinal.find()) {
                mensagem = mensagem + "Digite uma data válida, para o campo nascimento.<br />";
                retorno = true;
            } else {
                dataFim = Utilitario.verificaData(dataFinal);
            }

            if (retorno) {
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("titulo", "Reltórios do Sistema");
                request.setAttribute("arquivo", "formPesquisaRelatorios.jsp");
                return "restrito/relatorios.jsp";
            } else {

                request.setAttribute("pedidos", pedidoDAO.buscarTodosOsPedidosEntre(dataI, dataFim));
                request.setAttribute("dataInicial", dataInicial);
                request.setAttribute("dataFinal", dataFinal);
            }

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("titulo", "Reltórios de Pedidos do Sistema");
        request.setAttribute("arquivo", "listaDePedidos.jsp");
        return "restrito/relatorios.jsp";
    }
}
