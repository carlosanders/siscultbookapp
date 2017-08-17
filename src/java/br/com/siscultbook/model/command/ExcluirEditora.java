/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.InterfaceEditoraDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ExcluirEditora implements InterfaceCommand {

    private InterfaceEditoraDAO editoraDAO;

    public ExcluirEditora(InterfaceEditoraDAO editoraDAO) {
        super();
        this.editoraDAO = editoraDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";

        try {
            editoraDAO.excluir(Integer.valueOf(codigo));
            request.setAttribute("mensagem", "Editora código: " + codigo + " excluída com sucesso!");
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Código inválido" + codigo);
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        titulo += "Consulta - Editora";
        request.setAttribute("titulo", titulo);
        return "SisCultbookController?cmd=consultarEditora";
    }
}
