/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ConsultarNivelAcesso implements InterfaceCommand {

    private NivelDeAcessoDAO acessoDAO;

    public ConsultarNivelAcesso(NivelDeAcessoDAO acessoDAO) {
        super();
        this.acessoDAO = acessoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("titulo", "Consulta - Acessos");

        try {

            request.setAttribute("nivelDeAcesso", acessoDAO.retornarListaDeNiveisAcessos());

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        return "restrito/consultaNivelAcesso.jsp";
    }

}
