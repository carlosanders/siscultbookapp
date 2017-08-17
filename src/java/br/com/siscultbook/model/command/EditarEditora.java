/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Editora;
import br.com.siscultbook.model.dao.InterfaceEditoraDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class EditarEditora implements InterfaceCommand {

    private InterfaceEditoraDAO editoraDAO;

    public EditarEditora(InterfaceEditoraDAO editoraDAO) {
        super();
        this.editoraDAO = editoraDAO;

    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";

        if (codigo == null) {
            titulo = titulo + "Cadastro - Editora";
            request.setAttribute("titulo", titulo);
            return "restrito/cadastroEditora.jsp";
        }
        try {

            titulo = titulo + "Atualização - Editora";
            request.setAttribute("titulo", titulo);
            Editora editora = new Editora();
            editora = editoraDAO.getEditora(Integer.valueOf(codigo));
            request.setAttribute("editora", editoraDAO.getEditora(Integer.valueOf(codigo)));
            request.setAttribute("editoraStatus", editora.getStatus());

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        
        return "restrito/atualizaEditora.jsp";
    }
}
