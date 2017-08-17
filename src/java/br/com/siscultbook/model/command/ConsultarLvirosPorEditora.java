package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.InterfaceEditoraDAO;
import br.com.siscultbook.model.dao.InterfaceLivroDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ConsultarLvirosPorEditora implements InterfaceCommand {

    private InterfaceLivroDAO livroDAO;
    private InterfaceEditoraDAO editoraDAO;

    public ConsultarLvirosPorEditora(InterfaceLivroDAO livroDAO, InterfaceEditoraDAO editoraDAO) {
        super();
        this.livroDAO = livroDAO;
        this.editoraDAO = editoraDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("titulo", "Consulta Obras da Editora: ");
        String codigo;
        codigo = request.getParameter("codigo");

        try {
            if (codigo == null || codigo.equals("")) {
                request.setAttribute("erro", "Erro: código incorreto ou não encontrado no sitema!");
                return "SisCultbookController?cmd=arquivosEstaticos&pg=editoras";
            }

            //System.out.println("passou por aqui" + livroDAO.getLivrosPorAutor(Integer.parseInt(codigo)).size());
            if (livroDAO.getLivrosPorEditora(Integer.parseInt(codigo)).isEmpty()) {
                //System.out.println("passou por aqui" + livroDAO.getLivrosPorAutor(Integer.parseInt(codigo)));
                request.setAttribute("erro", "Erro: código incorreto ou não encontrado no sitema!");
                return "SisCultbookController?cmd=arquivosEstaticos&pg=editoras";
            }

            request.setAttribute("livros", livroDAO.getLivrosPorEditora(Integer.parseInt(codigo)));
            request.setAttribute("editoraProcurada", editoraDAO.getEditora(Integer.parseInt(codigo)));
            request.setAttribute("totalLivroDaEditora", livroDAO.totalDeLivrosDaEditora(Integer.parseInt(codigo)));

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
        }

        request.setAttribute("arquivo", "livrosPorEditora.jsp");
        return "arquivosEstaticos.jsp";
    }
}
