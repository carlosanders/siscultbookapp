package br.com.siscultbook.model.command;

import br.com.siscultbook.model.dao.InterfaceFuncionarioDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class ExcluirFuncionario implements InterfaceCommand {

    private InterfaceFuncionarioDAO funcionarioDAO;

    public ExcluirFuncionario(InterfaceFuncionarioDAO funcionarioDAO) {
        super();
        this.funcionarioDAO = funcionarioDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";

        try {
            funcionarioDAO.excluir(Integer.valueOf(codigo));
            request.setAttribute("mensagem", "Funcionário código: " + codigo + " excluído com sucesso!");
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Código inválido" + codigo);
            e.printStackTrace();
        } catch (Exception e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        titulo += "Consulta - Funcionário";
        request.setAttribute("titulo", titulo);
        return "SisCultbookController?cmd=consultarFuncionario";
    }
}
