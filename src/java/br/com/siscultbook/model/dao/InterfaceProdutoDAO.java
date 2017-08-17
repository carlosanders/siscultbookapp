package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Produto;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface InterfaceProdutoDAO {

    public void excluir(Integer codigo) throws SQLException;

    public void salvar(Produto produto) throws SQLException;

    public void atualizar(Produto produto) throws SQLException;

    public List<Produto> getProdutos() throws SQLException;

    public Integer recuperaCodigoProduto() throws SQLException;

}
