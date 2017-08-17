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
public class ConsultarLvirosPorAssunto implements InterfaceCommand {

    private InterfaceLivroDAO livroDAO;

    public ConsultarLvirosPorAssunto(InterfaceLivroDAO livroDAO) {
        super();
        this.livroDAO = livroDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("titulo", "Obras por categoria: ");
        String assunto;
        assunto = request.getParameter("assuntoLivro");

        try {

            System.out.println("passou por aqui" + request.getParameter("assuntoLivro"));
            if (assunto == null || assunto.equals("")) {
                request.setAttribute("erro", "Erro: assunto não encontrado no sitema!");
                return "SisCultbookController?cmd=arquivosEstaticos&pg=assuntos";
            }

            System.out.println("passou por aqui" + livroDAO.getLivrosPorAssunto(Assunto.valueOf(Assunto.class, assunto)).size());
            if (livroDAO.getLivrosPorAssunto(Assunto.valueOf(Assunto.class, assunto)).isEmpty()) {
                //System.out.println("passou por aqui" + livroDAO.getLivrosPorAutor(Integer.parseInt(codigo)));
                request.setAttribute("erro", "Erro: assunto não encontrado no sitema!");
                return "SisCultbookController?cmd=arquivosEstaticos&pg=assuntos";
            }

            request.setAttribute("livros", livroDAO.getLivrosPorAssunto(Assunto.valueOf(Assunto.class, assunto)));
            request.setAttribute("assuntoProcurado", Assunto.valueOf(Assunto.class, assunto));
            request.setAttribute("totalLivroDoAssunto", livroDAO.totalDeLivrosDoAssunto(Assunto.valueOf(Assunto.class, assunto)));

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("arquivo", "livrosPorAssunto.jsp");
        return "arquivosEstaticos.jsp";
    }
}
