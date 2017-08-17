/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Funcionario;
import br.com.siscultbook.model.dao.InterfaceFuncionarioDAO;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ConsultarFuncionario implements InterfaceCommand {

    private InterfaceFuncionarioDAO funcionarioDAO;

    public ConsultarFuncionario(InterfaceFuncionarioDAO funcionarioDAO) {
        super();
        this.funcionarioDAO = funcionarioDAO;

    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("titulo", "Consulta - Funcionário");

        try {

            request.setAttribute("funcionarios", funcionarioDAO.getFuncionarios());

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        return "restrito/consultaFuncionario.jsp";
    }
}
