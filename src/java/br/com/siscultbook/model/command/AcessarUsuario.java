/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author Carlos
 */
public class AcessarUsuario implements InterfaceCommand {

    private InterfaceFuncionarioDAO funcionarioDAO;
    private InterfaceClienteDAO clienteDAO;

    public AcessarUsuario(InterfaceFuncionarioDAO funcionarioDAO,
            InterfaceClienteDAO clienteDAO) {
        super();
        this.funcionarioDAO = funcionarioDAO;
        this.clienteDAO = clienteDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Funcionario funcionario = null;
        Cliente cliente = null;

        String login = request.getParameter("login");
        String senha = request.getParameter("senha");
        String usuario = request.getParameter("usuario");


        if (login == null || login.equals("")) {
            return "login.jsp";
        }
        if (usuario == null || usuario.equals("")) {
            return "login.jsp";
        }


        try {

            if (usuario.equals("funcionario")) {
                
                funcionario = funcionarioDAO.getFuncionario(login);

            } else {

                cliente = clienteDAO.getClienteLogin(login);
            }

            if (funcionario != null && funcionario.getLoginFuncionario().getSenha().equals(senha)) {

                request.getSession().setAttribute("usuario", funcionario);
                request.getSession().setAttribute("codigoUsuario", funcionario.getCodigoFuncionario());
                request.setAttribute("titulo", "Acesso Restrito");
                return "restrito/principal.jsp";

            } else if (cliente != null && cliente.getLoginCliente().getSenha().equals(senha)) {

                request.getSession().setAttribute("usuario", cliente);
                request.getSession().setAttribute("codigoUsuario", cliente.getCodigoCliente());
                //return "SisCultbookController?cmd=iniciarSiscultbook";
                return "SisCultbookController?cmd=paginaPrincipal";

            } else {
                //System.out.println("debug getFuncionario do form - " + senha);
                //request.setAttribute("erro", "Dados Incorretos"); <font color=\"#ff0000\">Problemas com a gravação:</font>
                request.setAttribute("mensagem", "Login ou senha inválido(s)");
                return "login.jsp";
            }

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com a atualização:</font> " + e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("mensagem", "Login ou senha inválido(s)");
        return "login.jsp";
    }
}
