package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.InterfaceAutorDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author carlosanders
 */
public class ConsultarAutor implements InterfaceCommand {

    private InterfaceAutorDAO autorDAO;

    public ConsultarAutor(InterfaceAutorDAO autorDAO) {
        super();
        this.autorDAO = autorDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("titulo", "Consulta - Autor(a)");

        try {

            request.setAttribute("autores", autorDAO.getAutores());

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        return "restrito/consultaAutor.jsp";
    }
}
