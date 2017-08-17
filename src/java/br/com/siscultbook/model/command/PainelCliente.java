package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.bean.Funcionario;
import br.com.siscultbook.model.dao.InterfaceClienteDAO;
import br.com.siscultbook.model.dao.InterfaceFuncionarioDAO;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import br.com.siscultbook.util.Utilitario;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
public class PainelCliente implements InterfaceCommand {

    private InterfaceClienteDAO clienteDAO;
    private InterfaceFuncionarioDAO funcionarioDAO;
    private NivelDeAcessoDAO acessoDAO;

    public PainelCliente(InterfaceClienteDAO clienteDAO, InterfaceFuncionarioDAO funcionarioDAO, NivelDeAcessoDAO acessoDAO) {
        super();
        this.clienteDAO = clienteDAO;
        this.funcionarioDAO = funcionarioDAO;
        this.acessoDAO = acessoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";
        HttpSession session = request.getSession();

        try {

            if (session.getAttribute("usuario") != null) {
                synchronized (session) {
                    if (codigo == null || codigo.equals("")) {
                        titulo = titulo + "Cadastro - Cliente";
                        request.setAttribute("titulo", titulo);
                        request.setAttribute("arquivo", "formCadastroCliente.jsp");
                        return "dadosCliente.jsp";
                    }

                    if (session.getAttribute("usuario") instanceof Cliente) {
                        Cliente cliente = new Cliente();
                        request.setAttribute("cliente", clienteDAO.getCliente(Integer.parseInt(codigo)));
                        cliente = clienteDAO.getCliente(Integer.parseInt(codigo));

                        request.setAttribute("clienteDataNascimento", Utilitario.DataFormatada(cliente.getDataNascimento()));
                        request.setAttribute("clienteDataCadastro", Utilitario.DataFormatada(cliente.getDesde()));
                        //request.setAttribute("clienteDataAcesso", Utilitario.DataFormatada(cliente.getLoginCliente().getUltimoAcesso()));

                        request.setAttribute("clienteEstadoCivil", cliente.getEstadoCivil());
                        //request.setAttribute("clienteNivelAcesso", cliente.getLoginCliente().getNivelDeAcesso().getCodigoNivelDeAcesso());
                        //System.out.println("debug cliente - " + cliente.getLoginCliente().getNivelDeAcesso().getCodigoNivelDeAcesso());
                        //request.setAttribute("clienteStatusUsuario", cliente.getLoginCliente().getStatus());
                        request.setAttribute("clienteSexo", cliente.getSexo());
                        request.setAttribute("clienteEstado", cliente.getEndereco().getEstado());
                    } else {
                        Funcionario funcionario = new Funcionario();
                        funcionario = funcionarioDAO.getFuncionario(Integer.parseInt(codigo));
                        request.setAttribute("funcionario", funcionarioDAO.getFuncionario(Integer.parseInt(codigo)));
                        
                        request.setAttribute("funcionarioDataNascimento", Utilitario.DataFormatada(funcionario.getDataNascimento()));
                        request.setAttribute("funcionarioSexo", funcionario.getSexo());
                        request.setAttribute("funcionarioCargo", funcionario.getCargo());
                        request.setAttribute("funcionarioDataAdmissao", Utilitario.DataFormatada(funcionario.getDataAdmissao()));
                        request.setAttribute("funcionarioEstado", funcionario.getEndereco().getEstado());
                        Integer codigoNivel = funcionario.getLoginFuncionario().getNivelDeAcesso().getCodigoNivelDeAcesso();
                        request.setAttribute("funcionarioNivelAcesso", acessoDAO.retornarUmAcesso(codigoNivel).getNivelDeAcesso().getNivelDeAcesso());
                        request.setAttribute("funcionarioStatusUsuario", funcionario.getLoginFuncionario().getStatus());

                        titulo = titulo + "Meus Dados";
                        request.setAttribute("titulo", titulo);
                        request.setAttribute("arquivo", "formAtualizaFuncionario.jsp");
                        return "dadosCliente.jsp";

                    }
                }

            } else {
                titulo = titulo + "Cadastro - Cliente";
                request.setAttribute("titulo", titulo);
                request.setAttribute("arquivo", "formCadastroCliente.jsp");
                return "dadosCliente.jsp";
            }

            if (codigo == null) {
                titulo = titulo + "Cadastro - Cliente";
                request.setAttribute("titulo", titulo);
                request.setAttribute("arquivo", "formCadastroCliente.jsp");
                return "dadosCliente.jsp";
            }


        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        titulo = titulo + "Meus Dados";
        request.setAttribute("titulo", titulo);
        request.setAttribute("arquivo", "formAtualizaCliente.jsp");
        return "dadosCliente.jsp";
    }
}
