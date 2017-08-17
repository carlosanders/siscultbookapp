package br.com.siscultbook.model.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface InterfaceCommand {

    public String execute(HttpServletRequest request,
            HttpServletResponse response);
}
