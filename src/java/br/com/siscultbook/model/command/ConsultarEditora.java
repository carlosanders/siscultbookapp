/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.InterfaceEditoraDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ConsultarEditora implements InterfaceCommand {

    private InterfaceEditoraDAO editoraDAO;

    public ConsultarEditora(InterfaceEditoraDAO editoraDAO) {
        super();
        this.editoraDAO = editoraDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("titulo", "Consulta - Editora");

        try {

            request.setAttribute("editoras", editoraDAO.getEditoras());

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        return "restrito/consultaEditora.jsp";
    }
}
