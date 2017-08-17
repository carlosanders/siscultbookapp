package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Acesso;
import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.bean.Funcionario;
import br.com.siscultbook.bean.Usuario;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class VerificarAutorizacao implements InterfaceCommand {

    private NivelDeAcessoDAO acessoDAO;

    public VerificarAutorizacao(NivelDeAcessoDAO acessoDAO) {
        super();
        this.acessoDAO = acessoDAO;
    }

    /*
     * Se esse comando VerificarAutorizacao retornar nulo quer dizer que o usuario esta autorizado
     * qq outra coisa ou redireciona para a página de SisCultbookController?cmd=acessarUsuario ou
     * para a pag. acessoNegado.jsp
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String cmd = request.getParameter("cmd");
        //Usuario usuario = (Usuario)request.getSession().getAttribute("usuario");
        try {


            List<Acesso> listaComandos = acessoDAO.ListaDeAcessos();
            ArrayList<String> comandos = new ArrayList<String>();
            for (Acesso comando : listaComandos) {
                comandos.add(comando.getComando());
                //System.out.println("comandos do banco: " + comando.getComando());
            }

            //if (comandos.contains(cmd)) {
                //System.out.println("contem o comando: " + cmd);
           // }
            //se o usuario da sessao for nulo ou
            //System.out.println("usuario: " + request.getSession().getAttribute("usuario") + " cmd=" + cmd);
            if (request.getSession().getAttribute("usuario") == null && comandos.contains(cmd)) {
                //System.out.print("Tem que se autenticar");
                return "SisCultbookController?cmd=acessarUsuario";
            }


            if (request.getSession().getAttribute("usuario") instanceof Cliente) {
                Cliente usuario = (Cliente) request.getSession().getAttribute("usuario");
                Map<String, Acesso> acessos = acessoDAO.getAcessosNivel(usuario.getLoginCliente().getNivelDeAcesso().getCodigoNivelDeAcesso());
                Acesso acesso = acessos.get(cmd);
                if (acesso != null && acesso.getNivelDeAcesso().getCodigoNivelDeAcesso() != usuario.getLoginCliente().getNivelDeAcesso().getCodigoNivelDeAcesso()) {
                    request.setAttribute("titulo", "Acesso negado");
                    request.setAttribute("mensagem", "Nome/Cód. da Transação: " + acesso.getDescricao() + "/" + acesso.getComando());
                    return "restrito/acessoNegado.jsp";
                }
            }
            if (request.getSession().getAttribute("usuario") instanceof Funcionario) {
                Funcionario usuario = (Funcionario) request.getSession().getAttribute("usuario");
                Map<String, Acesso> acessos = acessoDAO.getAcessosNivel(usuario.getLoginFuncionario().getNivelDeAcesso().getCodigoNivelDeAcesso());
                Acesso acesso = acessos.get(cmd);
                if (acesso != null && acesso.getNivelDeAcesso().getCodigoNivelDeAcesso() != usuario.getLoginFuncionario().getNivelDeAcesso().getCodigoNivelDeAcesso()) {
                    request.setAttribute("titulo", "Acesso negado");
                    request.setAttribute("mensagem", "Nome/Cód. da Transação: " + acesso.getDescricao() + "/" + acesso.getComando());
                    return "restrito/acessoNegado.jsp";
                }
            }

        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        return null;
    }
}
