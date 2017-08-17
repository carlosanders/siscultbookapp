/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Entrega;
import br.com.siscultbook.bean.Estados;
import br.com.siscultbook.conexao.InterfacePool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author com1dn-711
 */
public class EntregaDAO {

    private InterfacePool pool;

    public EntregaDAO(InterfacePool pool) {
        this.pool = pool;
    }

    public void salvarPagamento(Entrega entrega) throws SQLException {

        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlInsert = "INSERT INTO entrega (codigopedido, frete, nomecompleto, rua, bairro, cidade, "
                + "estado, cep, telefone)"
                + "VALUES (?,?,?,?,?,?,?,?,?);";
        try {
            ps = con.prepareCall(sqlInsert);
            setTabelaEntrega(entrega, ps);
            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizar(Entrega entrega) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE entrega SET codigopedido = ?, frete = ?, nomecompleto = ?, "
                + "rua = ?, bairro = ?, cidade = ?, estado = ?, cep = ?, telefone = ? "
                + "WHERE codigoentrega = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            setTabelaEntrega(entrega, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(10, entrega.getCodigoEntrega());

            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public Entrega getEntrega(Integer codigo) throws SQLException {
        Entrega entrega = null;
        Connection con = null;

        try {

            con = pool.getConnection();
            PreparedStatement ps;
            String sqlSelect = "select * "
                    + "from entrega "
                    + "where codigopedido = ? "
                    + "order by codigoentrega ASC;";

            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            List<Entrega> resultado = getListaEntrega(rs);
            if (resultado.size() > 0) {
                entrega = resultado.get(0);
            }

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return entrega;
    }

    private void setTabelaEntrega(Entrega entrega, PreparedStatement ps)
            throws SQLException {

        ps.setObject(1, entrega.getPedido().getCodigoPedido());
        ps.setObject(2, entrega.getFrete());
        ps.setObject(3, entrega.getNomeCompleto());
        ps.setObject(4, entrega.getEndereco().getRua());
        ps.setObject(5, entrega.getEndereco().getBairro());
        ps.setObject(6, entrega.getEndereco().getCidade());
        ps.setObject(7, entrega.getEndereco().getEstado().name());
        ps.setObject(8, entrega.getEndereco().getCep());
        ps.setObject(9, entrega.getTelefone());
    }

    private List<Entrega> getListaEntrega(ResultSet rs)
            throws SQLException {
        List<Entrega> resultado = new ArrayList<Entrega>();

        while (rs.next()) {

            Entrega entrega = new Entrega();

            entrega.setCodigoEntrega(rs.getInt("codigoentrega"));
            entrega.getPedido().setCodigoPedido(rs.getInt("codigopedido"));
            entrega.setFrete(rs.getDouble("frete"));
            entrega.setNomeCompleto(rs.getString("nomecompleto"));
            entrega.setTelefone(rs.getString("telefone"));
            //dados do endereço
            entrega.getEndereco().setRua(rs.getString("rua"));
            entrega.getEndereco().setBairro(rs.getString("bairro"));
            entrega.getEndereco().setCidade(rs.getString("cidade"));
            entrega.getEndereco().setCep(rs.getString("cep"));
            entrega.getEndereco().setEstado(Estados.valueOf(Estados.class, rs.getString("estado")));

            resultado.add(entrega);

        }

        return resultado;
    }
    
}
