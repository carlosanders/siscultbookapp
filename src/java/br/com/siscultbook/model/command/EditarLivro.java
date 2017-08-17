/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Autor;
import br.com.siscultbook.bean.Livro;
import br.com.siscultbook.model.dao.InterfaceAutorDAO;
import br.com.siscultbook.model.dao.InterfaceEditoraDAO;
import br.com.siscultbook.model.dao.InterfaceLivroDAO;
import java.io.File;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class EditarLivro implements InterfaceCommand {

    private InterfaceAutorDAO autorDAO;
    private InterfaceEditoraDAO editoraDAO;
    private InterfaceLivroDAO livroDAO;

    public EditarLivro(InterfaceAutorDAO autorDAO, InterfaceEditoraDAO editoraDAO,
            InterfaceLivroDAO livroDAO) {
        super();
        this.autorDAO = autorDAO;
        this.editoraDAO = editoraDAO;
        this.livroDAO = livroDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";

        try {
            request.setAttribute("editoras", editoraDAO.getEditorasAtivas());
            request.setAttribute("autores", autorDAO.getAutoresAtivos());

            System.out.println("Valor da codigo do Servlet: " + "\n" + codigo);
            if (codigo == null) {
                titulo = titulo + "Cadastro - Livro";
                request.setAttribute("titulo", titulo);
                return "restrito/cadastroLivroImg.jsp";
            }

            Livro livro = new Livro();
            livro = livroDAO.getLivro(Integer.parseInt(codigo));
            List<Autor> autores = livroDAO.lerAutores(Integer.parseInt(codigo));
            Set<Integer> hashTest = new HashSet<Integer>();
            for (int i = 0; i < autores.size(); i++) {
                //adiciono no Set somente o codigo do Autor e vou
                //enviar pelo request para autoresDoLivro
                hashTest.add(autores.get(i).getCodigoAutor());
            }

            //dados relativos ao livro dadastrado
            request.setAttribute("autoresDoLivro", hashTest);
            request.setAttribute("livroAssunto", livro.getAssunto());
            request.setAttribute("produto", livro);
            request.setAttribute("produtoEditora", livro.getEditora().getCodigoEditora());
            request.setAttribute("paginaInicial", livro.getPaginaPrincipal());
            System.out.println("valor do status livro: " + "\n" + livro.getStatus());
            request.setAttribute("livroStatus", livro.getStatus());


        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
        }

        request.setAttribute("titulo", "Conuslta - Livro");
        return "restrito/atualizaLivro.jsp";
    }
}
