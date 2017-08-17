package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.bean.Funcionario;
import br.com.siscultbook.bean.Pedido;
import br.com.siscultbook.model.dao.PedidoDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
public class ConsultarTodosPedidos implements InterfaceCommand {

    private PedidoDAO pedidoDAO;

    public ConsultarTodosPedidos(PedidoDAO pedidoDAO) {
        super();
        this.pedidoDAO = pedidoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("titulo", "Consulta de Pedidos");
        HttpSession session = request.getSession(true);

        try {
            if (session.getAttribute("usuario") != null && session.getAttribute("usuario") instanceof Funcionario) {

                //System.out.println("codigoCliente: " + cliente.getCodigoCliente());
                request.setAttribute("pedidos", pedidoDAO.buscarTodosOsPedidos());
            } else {
                
                return "restrito/acessoNegado.jsp";
            }

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        //request.setAttribute("arquivo", "listaPedidosDoCliente.jsp");
        return "restrito/consultaPedido.jsp";

    }
}
