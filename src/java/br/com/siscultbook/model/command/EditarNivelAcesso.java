/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Acesso;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class EditarNivelAcesso implements InterfaceCommand {

    private NivelDeAcessoDAO acessoDAO;

    public EditarNivelAcesso(NivelDeAcessoDAO acessoDAO) {
        super();
        this.acessoDAO = acessoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

       String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";

        if (codigo == null) {
            titulo = titulo + "Cadastro - Acesso";
            request.setAttribute("titulo", titulo);
            return "restrito/cadastroNivelAcesso.jsp";
        }
        try {

            titulo = titulo + "Atualização - Acesso";
            request.setAttribute("titulo", titulo);
            request.setAttribute("nivel", acessoDAO.retornarUmAcesso(Integer.parseInt(codigo)));
            

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }


        return "restrito/atualizaNivelDeAcesso.jsp";
    }
}

