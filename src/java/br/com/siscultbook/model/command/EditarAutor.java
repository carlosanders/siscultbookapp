/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Autor;
import br.com.siscultbook.model.dao.InterfaceAutorDAO;
import br.com.siscultbook.util.Utilitario;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class EditarAutor implements InterfaceCommand {

    private InterfaceAutorDAO autorDAO;

    public EditarAutor(InterfaceAutorDAO autorDAO) {
        super();
        this.autorDAO = autorDAO;

    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";

        if (codigo == null) {
            titulo = titulo + "Cadastro - Autor(a)";
            request.setAttribute("titulo", titulo);
            return "restrito/cadastroAutor.jsp";
        }
        try {

            titulo = titulo + "Atualização - Autor(a)";
            request.setAttribute("titulo", titulo);

            request.setAttribute("autor", autorDAO.getAutor(Integer.valueOf(codigo)));
            
            Autor autor = new Autor();
            autor = autorDAO.getAutor(Integer.valueOf(codigo));
            request.setAttribute("autorDataNascimento", Utilitario.DataFormatada(autor.getDataNascimento()));
            request.setAttribute("autorSexo", autor.getSexo());
            request.setAttribute("autorEstado", autor.getEndereco().getEstado());
            request.setAttribute("autorStatus", autor.getStatus());

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }


        return "restrito/atualizaAutor.jsp";
    }

}
