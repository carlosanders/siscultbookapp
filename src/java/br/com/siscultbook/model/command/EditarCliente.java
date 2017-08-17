package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.model.dao.InterfaceClienteDAO;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import br.com.siscultbook.util.Utilitario;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class EditarCliente implements InterfaceCommand {

    private InterfaceClienteDAO clienteDAO;
    private NivelDeAcessoDAO acessoDAO;

    public EditarCliente(InterfaceClienteDAO clienteDAO, NivelDeAcessoDAO acessoDAO) {
        super();
        this.clienteDAO = clienteDAO;
        this.acessoDAO = acessoDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";

        try {

            request.setAttribute("NiveisDeAcesso", acessoDAO.getNiveisDeAcesso());

            if (codigo == null) {
                titulo = titulo + "Cadastro - Cliente";
                request.setAttribute("titulo", titulo);
                return "restrito/cadastroCliente.jsp";
            }

            Cliente cliente = new Cliente();
            request.setAttribute("cliente", clienteDAO.getCliente(Integer.parseInt(codigo)));
            cliente = clienteDAO.getCliente(Integer.valueOf(codigo));

            request.setAttribute("clienteDataNascimento", Utilitario.DataFormatada(cliente.getDataNascimento()));
            request.setAttribute("clienteDataCadastro", Utilitario.DataFormatada(cliente.getDesde()));
            request.setAttribute("clienteDataAcesso", Utilitario.DataFormatada(cliente.getLoginCliente().getUltimoAcesso()));

            request.setAttribute("clienteEstadoCivil", cliente.getEstadoCivil());
            request.setAttribute("clienteNivelAcesso", cliente.getLoginCliente().getNivelDeAcesso().getCodigoNivelDeAcesso());
            //System.out.println("debug cliente - " + cliente.getLoginCliente().getNivelDeAcesso().getCodigoNivelDeAcesso());
            request.setAttribute("clienteStatusUsuario", cliente.getLoginCliente().getStatus());
            request.setAttribute("clienteSexo", cliente.getSexo());
            request.setAttribute("clienteEstado", cliente.getEndereco().getEstado());


        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        titulo = titulo + "Atualização - Cliente";
        request.setAttribute("titulo", titulo);


        return "restrito/atualizaCliente.jsp";
    }
}
