package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.InterfaceLivroDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class IniciarSisCultook implements InterfaceCommand {

    private InterfaceLivroDAO livroDAO;

    public IniciarSisCultook(InterfaceLivroDAO livroDAO) {
        super();
        this.livroDAO = livroDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("titulo", "Página Inicial");

        try {

            request.setAttribute("livros", livroDAO.getLivrosPaginaPrincipal());

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        return "index.jsp";
    }
}
