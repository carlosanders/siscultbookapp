package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Carrinho;
import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.bean.Funcionario;
import br.com.siscultbook.model.dao.InterfaceClienteDAO;
import br.com.siscultbook.model.dao.InterfaceFuncionarioDAO;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author com1dn-711
 */
public class RemoverSessao implements InterfaceCommand {

    private InterfaceClienteDAO clienteDAO;
    private InterfaceFuncionarioDAO funcionarioDAO;

    public RemoverSessao(InterfaceClienteDAO clienteDAO, InterfaceFuncionarioDAO funcionarioDAO) {
        this.clienteDAO = clienteDAO;
        this.funcionarioDAO = funcionarioDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession(true);
        Carrinho carrinho;
        Cliente cliente;
        Funcionario funcionario;

        try {
            synchronized (session) {

                if (session.getAttribute("usuario") != null) {
                    if (session.getAttribute("usuario") instanceof Cliente) {
                        cliente = (Cliente) session.getAttribute("usuario");
                        cliente.getLoginCliente().setUltimoAcesso(this.getPegaDataAtual());
                        clienteDAO.atualizarUltimoAcesso(cliente.getCodigoCliente());
                    } else {
                        funcionario = (Funcionario) session.getAttribute("usuario");
                        funcionario.getLoginFuncionario().setUltimoAcesso(this.getPegaDataAtual());
                        funcionarioDAO.atualizarUltimoAcesso(funcionario.getCodigoFuncionario());
                    }
                }
                
                Locale locale = new Locale("pt", "BR");
                GregorianCalendar calendar = new GregorianCalendar();
                SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", locale);
                System.out.println(formatador.format(calendar.getTime()));                


            }
        } catch (SQLException e) {
            request.setAttribute("mensagem", "Problemas com a atualização: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor inválido: " + e.getMessage());
            e.printStackTrace();
        }
        session.invalidate();
        return "SisCultbookController?cmd=iniciarSiscultbook";
    }

    public GregorianCalendar getPegaDataAtual() {
        GregorianCalendar calendar = new GregorianCalendar();
        Timestamp now = new Timestamp( System.currentTimeMillis() ); // já tem data, hora, minuto e segundo
        calendar.setTime(now);
        //Date trialTime = new Date();
        //calendar.setTime(trialTime);
        return calendar;
    }
}
