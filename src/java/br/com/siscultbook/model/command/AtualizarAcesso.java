/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Acesso;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class AtualizarAcesso implements InterfaceCommand {

    private NivelDeAcessoDAO acessoDAO;

    public AtualizarAcesso(NivelDeAcessoDAO acessoDAO) {
        super();
        this.acessoDAO = acessoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Enumeration parametros = request.getParameterNames();
        List<Acesso> acessos = new ArrayList<Acesso>();
        Integer codigo = Integer.parseInt(request.getParameter("codigo"));

        while (parametros.hasMoreElements()) {
            String paramAcesso = (String) parametros.nextElement();
            //System.out.println("Parametros: " + parametros.nextElement());
            Acesso acesso = new Acesso();
            try {

                acesso.setCodigoAcesso(Integer.parseInt(paramAcesso));
                acesso.getNivelDeAcesso().setCodigoNivelDeAcesso(codigo);
                acessos.add(acesso);

            } catch (NumberFormatException e) {
                //e.printStackTrace();
            }
        }

        try{
            acessoDAO.excluir(codigo);
            acessoDAO.salvarListaAcessos(acessos);
            //System.out.println("Dados da Lista: " + acessos.get(codigo).getNivelDeAcesso().getCodigoNivelDeAcesso());
            request.setAttribute("mensagem", "Acessos atualizados com sucesso");
            
        }catch(SQLException e){
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problema com o acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("titulo", "Consulta - Acessos");
        return "SisCultbookController?cmd=consultarNivelAcesso";
    }
}
