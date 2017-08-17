package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Livro;
import br.com.siscultbook.model.dao.InterfaceAutorDAO;
import br.com.siscultbook.model.dao.InterfaceLivroDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ConsultarLivrosPorAutor implements InterfaceCommand {

    private InterfaceLivroDAO livroDAO;
    private InterfaceAutorDAO autorDAO;

    public ConsultarLivrosPorAutor(InterfaceLivroDAO livroDAO, InterfaceAutorDAO autorDAO) {
        super();
        this.livroDAO = livroDAO;
        this.autorDAO = autorDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("titulo", "Consulta Obras do Autor: ");
        String codigo;
        codigo = request.getParameter("codigo");

        try {
            if (codigo == null || codigo.equals("")) {
                request.setAttribute("erro", "Erro: código incorreto ou não encontrado no sitema!");
                return "SisCultbookController?cmd=arquivosEstaticos&pg=autores";
            }

            //System.out.println("passou por aqui" + livroDAO.getLivrosPorAutor(Integer.parseInt(codigo)).size());
            if (livroDAO.getLivrosPorAutor(Integer.parseInt(codigo)).isEmpty()) {
                //System.out.println("passou por aqui" + livroDAO.getLivrosPorAutor(Integer.parseInt(codigo)));
                request.setAttribute("erro", "Erro: código incorreto ou não encontrado no sitema!");
                return "SisCultbookController?cmd=arquivosEstaticos&pg=autores";
            }

            request.setAttribute("livros", livroDAO.getLivrosPorAutor(Integer.parseInt(codigo)));
            request.setAttribute("autorProcurado", autorDAO.getAutor(Integer.parseInt(codigo)));
            request.setAttribute("totalLivroDoAutor", livroDAO.totalDeLivrosDoAutor(Integer.parseInt(codigo)));

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
        }

        request.setAttribute("arquivo", "livrosPorAutor.jsp");
        return "arquivosEstaticos.jsp";
    }
}
