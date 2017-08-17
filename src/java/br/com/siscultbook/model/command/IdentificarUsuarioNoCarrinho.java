package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.bean.Funcionario;
import br.com.siscultbook.model.dao.InterfaceClienteDAO;
import br.com.siscultbook.model.dao.InterfaceFuncionarioDAO;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author com1dn-711
 */
public class IdentificarUsuarioNoCarrinho implements InterfaceCommand {

    private InterfaceClienteDAO clienteDAO;

    public IdentificarUsuarioNoCarrinho(InterfaceClienteDAO clienteDAO) {
        super();
        this.clienteDAO = clienteDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Cliente cliente = null;

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");

        if (login == null || login.equals("")) {
            return "SisCultbookController?cmd=consultarCatalogo";
        }

        try {

            cliente = clienteDAO.getClienteLogin(login);

            if (cliente != null && cliente.getLoginCliente().getSenha().equals(senha)) {
                request.getSession().setAttribute("usuario", cliente);
                request.getSession().setAttribute("codigoUsuario", cliente.getCodigoCliente());
                return "SisCultbookController?cmd=finalizarCompra";

            } else {
                //System.out.println("debug getFuncionario do form - " + senha);
                request.setAttribute("mensagem", "Login ou senha inválido(s), área restrita somente aos <font color=\"#ff0000\">\"clientes\"</font> da CULTBOOK LIVRARIA");
                request.setAttribute("titulo", "Identificação");
                request.setAttribute("arquivo", "identificacao.jsp");
                return "arquivosEstaticos.jsp";
            }

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("mensagem", "Login ou senha inválido(s)");
        request.setAttribute("titulo", "Identificação");
        request.setAttribute("arquivo", "identificacao.jsp");
        return "arquivosEstaticos.jsp";
    }
}
