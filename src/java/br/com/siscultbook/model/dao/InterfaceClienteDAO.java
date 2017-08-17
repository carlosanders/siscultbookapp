package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Cliente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface InterfaceClienteDAO {

    public void excluir(Integer codigo) throws SQLException;

    public void salvar(Cliente cliente) throws SQLException;

    public void atualizar(Cliente cliente) throws SQLException;

    public void atualizarUltimoAcesso(Integer codigoCliente) throws SQLException;

    public Cliente getCliente(Integer codigo) throws SQLException;

    public Cliente buscarClientePorCPF(String cpf) throws SQLException;

    public Cliente getClienteLogin(String cpf) throws SQLException;

    public List<Cliente> getClientes() throws SQLException;
}
