/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ExibrRelatorios implements InterfaceCommand {

    public ExibrRelatorios() {
        super();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("titulo", "Reltórios do Sistema");
        request.setAttribute("arquivo", "formPesquisaRelatorios.jsp");
        return "restrito/relatorios.jsp";
        
    }
}
