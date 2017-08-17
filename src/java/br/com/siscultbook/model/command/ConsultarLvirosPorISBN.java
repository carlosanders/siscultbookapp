package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Assunto;
import br.com.siscultbook.model.dao.InterfaceEditoraDAO;
import br.com.siscultbook.model.dao.InterfaceLivroDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ConsultarLvirosPorISBN implements InterfaceCommand {

    private InterfaceLivroDAO livroDAO;

    public ConsultarLvirosPorISBN(InterfaceLivroDAO livroDAO) {
        super();
        this.livroDAO = livroDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("titulo", "Obras por Tipo: ");
        String assunto;
        String busca;
        assunto = request.getParameter("tipoPesquisa");
        busca = request.getParameter("busca");

        try {

            System.out.println("passou por aqui" + request.getParameter("busca"));
            if (busca == null || busca.equals("")) {
                request.setAttribute("erro", "Erro: " + busca + " não encontrado no sitema!");
                return "SisCultbookController?cmd=paginaPrincipal";
            }

            if (assunto.equals("isbn")) {
                if (livroDAO.getLivrosPorISBN(busca).isEmpty()) {
                    request.setAttribute("erro", "Erro: " + busca + " não encontrado no sitema!");
                    return "SisCultbookController?cmd=paginaPrincipal";
                }
                request.setAttribute("livros", livroDAO.getLivrosPorISBN(busca));

            } else if (assunto.equals("titulo")) {
                if (livroDAO.getLivrosPorTitulo(busca).isEmpty()) {
                    request.setAttribute("erro", "Erro: " + busca + " não encontrado no sitema!");
                    return "SisCultbookController?cmd=paginaPrincipal";
                }
                request.setAttribute("livros", livroDAO.getLivrosPorTitulo(busca));
            }

            request.setAttribute("tipoProcurado", busca);

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("arquivo", "livrosPorTipo.jsp");
        return "arquivosEstaticos.jsp";
    }
}
