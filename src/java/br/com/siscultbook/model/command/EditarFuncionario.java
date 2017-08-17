package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Funcionario;
import br.com.siscultbook.model.dao.InterfaceFuncionarioDAO;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import br.com.siscultbook.util.Utilitario;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class EditarFuncionario implements InterfaceCommand {

    private InterfaceFuncionarioDAO funcionarioDAO;
    private NivelDeAcessoDAO acessoDAO;

    public EditarFuncionario(InterfaceFuncionarioDAO funcionarioDAO, NivelDeAcessoDAO acessoDAO) {
        super();
        this.funcionarioDAO = funcionarioDAO;
        this.acessoDAO = acessoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";



        try {
            request.setAttribute("NiveisDeAcesso", acessoDAO.getNiveisDeAcesso());

            if (codigo == null) {
                titulo = titulo + "Cadastro - Funcionario";
                request.setAttribute("titulo", titulo);
                return "restrito/cadastroFuncionario.jsp";
            }

            titulo = titulo + "Atualização - Funcionário";
            request.setAttribute("titulo", titulo);

            Funcionario funcionario = new Funcionario();
            request.setAttribute("funcionario", funcionarioDAO.getFuncionario(Integer.parseInt(codigo)));
            funcionario = funcionarioDAO.getFuncionario(Integer.parseInt(codigo));

            request.setAttribute("funcionarioDataNascimento", Utilitario.DataFormatada(funcionario.getDataNascimento()));
            request.setAttribute("funcionarioSexo", funcionario.getSexo());
            request.setAttribute("funcionarioCargo", funcionario.getCargo());
            request.setAttribute("funcionarioDataAdmissao", Utilitario.DataFormatada(funcionario.getDataAdmissao()));
            request.setAttribute("funcionarioDataDemissao", Utilitario.DataFormatada(funcionario.getDataDemissao()));
            request.setAttribute("funcionarioEstado", funcionario.getEndereco().getEstado());
            request.setAttribute("funcionarioNivelAcesso", funcionario.getLoginFuncionario().getNivelDeAcesso().getCodigoNivelDeAcesso());
            request.setAttribute("funcionarioDataAcesso", Utilitario.DataFormatadaComleta(funcionario.getLoginFuncionario().getUltimoAcesso()));
            request.setAttribute("funcionarioStatusUsuario", funcionario.getLoginFuncionario().getStatus());


        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        return "restrito/atualizaFuncionario.jsp";

    }
}
