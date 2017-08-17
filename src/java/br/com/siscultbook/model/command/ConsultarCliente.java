package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.ClienteDAO;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ConsultarCliente implements InterfaceCommand {

    private ClienteDAO clienteDAO;

    public ConsultarCliente(ClienteDAO clienteDAO) {
        super();
        this.clienteDAO = clienteDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("titulo", "Consulta - Cliente");

        try {
            
            request.setAttribute("clientes", clienteDAO.getClientes());

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        return "restrito/consultaCliente.jsp";
    }
}
