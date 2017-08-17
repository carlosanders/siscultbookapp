package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.InterfaceAutorDAO;
import br.com.siscultbook.model.dao.InterfaceEditoraDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class VisualizarArquivos implements InterfaceCommand {

    private InterfaceAutorDAO autorDAO;
    private InterfaceEditoraDAO editoraDAO;

    public VisualizarArquivos(InterfaceAutorDAO autorDAO, InterfaceEditoraDAO editoraDAO) {
        super();
        this.autorDAO = autorDAO;
        this.editoraDAO = editoraDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String pagina;
        try {
            if (request.getParameter("pg") == null || request.getParameter("pg").equals("")) {
                return "SisCultbookController?cmd=iniciarSiscultbook";
            } else {
                pagina = request.getParameter("pg");
            }


            if (pagina.equals("sobre")) {
                request.setAttribute("titulo", "Sobre a Cultbook");
                request.setAttribute("arquivo", "sobre.jsp");
                return "arquivosEstaticos.jsp";
            } else if (pagina.equals("politica")) {
                request.setAttribute("titulo", "Política de Vendas");
                request.setAttribute("arquivo", "politica.jsp");
                return "arquivosEstaticos.jsp";
            } else if (pagina.equals("missao")) {
                request.setAttribute("titulo", "Missão e Valores");
                request.setAttribute("arquivo", "missao.jsp");
                return "arquivosEstaticos.jsp";
            } else if (pagina.equals("fale")) {
                request.setAttribute("titulo", "Fale Conosco");
                request.setAttribute("arquivo", "fale.jsp");
                return "arquivosEstaticos.jsp";
            } else if (pagina.equals("autores")) {
                request.setAttribute("autores", autorDAO.getAutores());
                request.setAttribute("titulo", "Lista de Autores");
                request.setAttribute("arquivo", "ListaAutores.jsp");
                return "arquivosEstaticos.jsp";
            } else if (pagina.equals("editoras")) {
                request.setAttribute("editoras", editoraDAO.getEditoras());
                request.setAttribute("titulo", "Lista de Editoras");
                request.setAttribute("arquivo", "listaEditoras.jsp");
                return "arquivosEstaticos.jsp";
            } else if (pagina.equals("assuntos")) {
                request.setAttribute("titulo", "Lista de Assuntos dos livros");
                request.setAttribute("arquivo", "listaAssuntos.jsp");
                return "arquivosEstaticos.jsp";
            }

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        return "SisCultbookController?cmd=iniciarSiscultbook";
    }
}
