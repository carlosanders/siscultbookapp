/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Autor;
import br.com.siscultbook.bean.Livro;
import br.com.siscultbook.model.dao.InterfaceLivroDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class DetalhesProduto implements InterfaceCommand {

    private InterfaceLivroDAO livroDAO;

    public DetalhesProduto(InterfaceLivroDAO livroDAO) {
        super();
        this.livroDAO = livroDAO;

    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("titulo", "Detalhes do Produto");

        try {

            if (request.getParameter("cod") == null || request.getParameter("cod").equals("")) {
                request.setAttribute("mensagem", "Erro: O código é nulo ou vazio, por favor entre com um código válido");
                return "SisCultbookController?cmd=consultarCatalogo";
            } else {
                Integer codigo = Integer.parseInt(request.getParameter("cod"));
                Livro livro = new Livro();

                List<Autor> autores = livroDAO.lerAutores(codigo);

                livro = livroDAO.getLivro(codigo);
                request.setAttribute("mensagem", "Exibindo informações do Livro:");
                request.setAttribute("livro", livro);
                request.setAttribute("autoresDoLivro", autores);
            }

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        return "produto.jsp";

    }
}
