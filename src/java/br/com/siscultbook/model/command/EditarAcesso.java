package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Acesso;
import br.com.siscultbook.model.dao.InterfaceClienteDAO;
import br.com.siscultbook.model.dao.InterfaceFuncionarioDAO;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class EditarAcesso implements InterfaceCommand {

    private InterfaceFuncionarioDAO funcionarioDAO;
    private InterfaceClienteDAO clienteDAO;
    private NivelDeAcessoDAO acessoDAO;

    public EditarAcesso(InterfaceFuncionarioDAO funcionarioDAO, InterfaceClienteDAO clienteDAO,
            NivelDeAcessoDAO acessoDAO) {
        super();
        this.funcionarioDAO = funcionarioDAO;
        this.clienteDAO = clienteDAO;
        this.acessoDAO = acessoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String codigo = null;
        try {
            codigo = request.getParameter("codigo");

            request.setAttribute("perfil", acessoDAO.retornarUmAcesso(Integer.parseInt(codigo)));            
            request.setAttribute("acessos", acessoDAO.getAcessosNivel(Integer.parseInt(codigo)).values());
            //System.out.println("comando SQL - " + acessoDAO.getAcessosNivel(Integer.parseInt(codigo)).values());
            request.setAttribute("titulo", "Atualizar Acessos");

        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
            e.printStackTrace();
            return "SisCultbookController?cmd=editarNivelAcesso";
            
        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
            return "SisCultbookController?cmd=editarNivelAcesso";
        }

        return "restrito/atualizaAcesso.jsp";
    }
}
