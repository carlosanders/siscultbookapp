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
public class ExcluirArquivoImg implements InterfaceCommand {

    private InterfaceAutorDAO autorDAO;
    private InterfaceEditoraDAO editoraDAO;
    private InterfaceLivroDAO livroDAO;

    public ExcluirArquivoImg(InterfaceAutorDAO autorDAO, InterfaceEditoraDAO editoraDAO,
            InterfaceLivroDAO livroDAO) {
        super();
        this.autorDAO = autorDAO;
        this.editoraDAO = editoraDAO;
        this.livroDAO = livroDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String codigo;
        codigo = request.getParameter("codigo");

        try {
            request.setAttribute("editoras", editoraDAO.getEditoras());
            request.setAttribute("autores", autorDAO.getAutores());

            Livro livro = new Livro();
            livro = livroDAO.getLivro(Integer.parseInt(codigo));
            List<Autor> autores = livroDAO.lerAutores(Integer.parseInt(codigo));
            Set<Integer> hashTest = new HashSet<Integer>();
            for (int i = 0; i < autores.size(); i++) {
                //adiciono no Set somente o codigo do Autor e vou
                //enviar pelo request para autoresDoLivro
                hashTest.add(autores.get(i).getCodigoAutor());
                System.out.println("codigo autor do livro - " + autores.get(i).getSobrenome());
            }
            //dados relativos ao livro dadastrado
            request.setAttribute("autoresDoLivro", hashTest);
            request.setAttribute("livroAssunto", livro.getAssunto());
            request.setAttribute("produto", livro);
            request.setAttribute("codigoEditoraCadastrada", livro.getEditora().getCodigoEditora());

            //String opcao = request.getParameter("opcao");
            if (request.getParameter("opcao") != null) {
                String img = request.getParameter("img");
                String path = request.getAttribute("dirAplicacao") + "/imgensLivros/";

                File delFile1 = new File(path, img);
                delFile1.delete();

                System.out.println("caminho da exlcusao: " + path + img);
                request.setAttribute("mensagem", "Arquivo excluído com sucesso");
                //livro.setFigura(null);
                livroDAO.atualizarFigura(Integer.parseInt(codigo));
                
                livro = livroDAO.getLivro(Integer.parseInt(codigo));
                request.setAttribute("autoresDoLivro", hashTest);
                request.setAttribute("livroAssunto", livro.getAssunto());
                request.setAttribute("produto", livro);
                request.setAttribute("produtoEditora", livro.getEditora().getCodigoEditora());

                System.out.println("Valor da Figura: " + "\n" + livro.getFigura());

            }


        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
        }

        request.setAttribute("titulo", "Consulta - Livro");
        return "restrito/atualizaLivro.jsp";

    }
}
