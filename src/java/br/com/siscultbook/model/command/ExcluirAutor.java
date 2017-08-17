package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.InterfaceAutorDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlosanders
 */
public class ExcluirAutor implements InterfaceCommand {

    private InterfaceAutorDAO autorDAO;

    public ExcluirAutor(InterfaceAutorDAO autorDAO) {
        super();
        this.autorDAO = autorDAO;

    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";

        try {
            autorDAO.excluir(Integer.valueOf(codigo));
            request.setAttribute("mensagem", "Autor código: " + codigo + " excluído com sucesso!");
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Código inválido" + codigo);
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        titulo += "Consulta - Autor";
        request.setAttribute("titulo", titulo);
        return "SisCultbookController?cmd=consultarAutor";
    }
}
