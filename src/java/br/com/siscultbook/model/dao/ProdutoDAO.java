/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Livro;
import br.com.siscultbook.bean.Produto;
import br.com.siscultbook.conexao.InterfacePool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class ProdutoDAO implements InterfaceProdutoDAO {

    private InterfacePool pool;

    public ProdutoDAO(InterfacePool pool) {
        this.pool = pool;
    }

    public void excluir(Integer codigo) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlExcluir = "DELETE FROM produto WHERE codigoproduto = ?;";

        try {

            ps = con.prepareStatement(sqlExcluir);
            ps.setInt(1, codigo);
            System.out.println("debug excluirProduto - " + ps);
            ps.execute();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
    }

    public void salvar(Produto produto) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlInsertProduto = "INSERT INTO produto (descricaocurta, descricaolonga, preco)"
                + "VALUES (?,?,?);";

        try {

            ps = con.prepareCall(sqlInsertProduto);
            setTabelaProduto(produto, ps);
            System.out.println("debug carlos - " + produto.getDescricaoCurta());
            ps.executeUpdate();

            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizar(Produto produto) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE produto SET descricaocurta = ?, descricaolonga = ?, preco = ?"
                + " WHERE codigoproduto = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            setTabelaProduto(produto, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(4, produto.getCodigoProduto());

            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public List<Produto> getProdutos() throws SQLException {
        List<Produto> resultado = new ArrayList<Produto>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * FROM produto";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaProduto(rs);

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public Integer recuperaCodigoProduto() throws SQLException {

        PreparedStatement ps = null;
        Connection con = pool.getConnection();
        String sql = "SELECT MAX(codigoproduto) FROM produto;";
        ResultSet rs = null;
        int codigoProduto = 0;

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            codigoProduto = rs.getInt(1);
            System.out.println("debug recuperaCodigoProduto - " + codigoProduto);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return codigoProduto;

    }

    private void setTabelaProduto(Produto produto, PreparedStatement ps)
            throws SQLException {

        ps.setObject(1, produto.getDescricaoCurta());
        ps.setObject(2, produto.getDescricaoLonga());
        ps.setObject(3, produto.getPreco());

    }

    private List<Produto> getListaProduto(ResultSet rs) throws SQLException {

        List<Produto> resultado = new ArrayList<Produto>();

        while (rs.next()) {

            Produto produto = new Livro();

            produto.setCodigoProduto(rs.getInt("codigoproduto"));
            produto.setDescricaoCurta(rs.getString("descricaocurta"));
            produto.setDescricaoLonga(rs.getString("descricaolonga"));
            produto.setPreco(rs.getDouble("preco"));

            resultado.add(produto);

        }

        return resultado;
    }
}
