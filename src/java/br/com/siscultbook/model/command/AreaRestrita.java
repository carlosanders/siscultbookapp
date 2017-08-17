package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Cliente;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author com1dn-711
 */
public class AreaRestrita implements InterfaceCommand {

    public AreaRestrita() {
        super();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);

        if (session.getAttribute("usuario") == null) {
            return "SisCultbookController?cmd=acessarUsuario";
        } else {
            if (session.getAttribute("usuario") instanceof Cliente) {
                return "SisCultbookController?cmd=paginaPrincipal";
            } else {
                request.setAttribute("titulo", "Acesso Restrito");
                return "restrito/principal.jsp";
            }
        }
    }
}
