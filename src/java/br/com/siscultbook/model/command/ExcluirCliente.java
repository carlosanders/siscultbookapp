package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.InterfaceClienteDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ExcluirCliente implements InterfaceCommand {

    private InterfaceClienteDAO clienteDAO;

    public ExcluirCliente(InterfaceClienteDAO clienteDAO) {
        super();
        this.clienteDAO = clienteDAO;

    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";

        try {
            clienteDAO.excluir(Integer.valueOf(codigo));            
            request.setAttribute("mensagem", "Cliente excluído com sucesso!");
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Código inválido" + codigo);
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        titulo += "Consulta - Cliente";
        request.setAttribute("titulo", titulo);
        return "SisCultbookController?cmd=consultarCliente";
    }
}
