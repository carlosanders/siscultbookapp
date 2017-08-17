/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.InterfaceLivroDAO;
import br.com.siscultbook.model.dao.InterfaceProdutoDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ExcluirLivro implements InterfaceCommand {

    private InterfaceLivroDAO livroDAO;
    private InterfaceProdutoDAO produtoDAO;

    public ExcluirLivro(InterfaceLivroDAO livroDAO, InterfaceProdutoDAO produtoDAO){
        super();
        this.livroDAO = livroDAO;
        this.produtoDAO = produtoDAO;

    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        
        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";

        try {

            
            livroDAO.excluirAutores(Integer.parseInt(codigo));
            livroDAO.excluir(Integer.parseInt(codigo));
            produtoDAO.excluir(Integer.parseInt(codigo));

            request.setAttribute("mensagem", "Livro excluído com sucesso!");
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Código inválido" + codigo);
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        titulo += "Consulta - Livro";
        request.setAttribute("titulo", titulo);
        return "SisCultbookController?cmd=consultarLivro";
    }

}
