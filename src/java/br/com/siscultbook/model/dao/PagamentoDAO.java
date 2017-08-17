/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Pagamento;
import br.com.siscultbook.bean.PagamentoBoleto;
import br.com.siscultbook.bean.PagamentoCartao;
import br.com.siscultbook.bean.TipoCartao;
import br.com.siscultbook.conexao.InterfacePool;
import br.com.siscultbook.util.Utilitario;
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
public class PagamentoDAO {

    private InterfacePool pool;

    public PagamentoDAO(InterfacePool pool) {
        this.pool = pool;
    }

    public void salvarPagamento(Pagamento pagamento) throws SQLException {

        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlInsert = "INSERT INTO pagamento (codigopedido, valortotal)"
                + "VALUES (?,?);";
        try {
            ps = con.prepareCall(sqlInsert);
            setTabelaPagamento(pagamento, ps);
            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizarPagamento(Pagamento pagamento) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE pagamento SET codigopedido = ?, valortotal = ?"
                + " WHERE codigopagamento = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            setTabelaPagamento(pagamento, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(3, pagamento.getCodigoPagamento());

            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void salvarPagamentoBoleto(PagamentoBoleto pagamento) throws SQLException {

        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlInsert = "INSERT INTO pagamentoboleto (codigopagamento, numerodocumento, vencimento, cedente)"
                + "VALUES (?,?,?,?);";
        try {
            ps = con.prepareCall(sqlInsert);
            setTabelaPagamentoBoleto(pagamento, ps);
            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizarPagamentoBoleto(PagamentoBoleto pagamento) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE pagamentoboleto SET codigopagamento = ?, numerodocumento = ?, vencimento = ?, cedente = ?"
                + " WHERE codigopagamento = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            setTabelaPagamentoBoleto(pagamento, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(5, pagamento.getCodigoPagamento());

            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void salvarPagamentoCartao(PagamentoCartao pagamento) throws SQLException {

        Connection con = pool.getConnection();
        PreparedStatement ps;


        String sqlInsert = "INSERT INTO pagamentocartao (codigopagamento, numerocartao, validadecartao, bandeira, "
                + "codigoseguranca, nometitular, numeroparcelas, valorparcela)"
                + "VALUES (?,?,?,?,?,?,?,?);";
        try {
            ps = con.prepareCall(sqlInsert);
            setTabelaPagamentoCartao(pagamento, ps);
            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizarPagamentoCartao(PagamentoCartao pagamento) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE pagamentocartao SET codigopagamento = ?, numerocartao = ?, validadecartao = ?,"
                + "bandeira = ?, codigoseguranca = ?, nometitular = ?, numeroparcelas = ?, valorparcela = ?"
                + " WHERE codigopagamento = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            setTabelaPagamentoCartao(pagamento, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(9, pagamento.getCodigoPagamento());
            System.out.println("sql do atualizarCArtao" + ps);
            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public Integer recuperaCodigoPagamento() throws SQLException {

        PreparedStatement ps = null;
        Connection con = pool.getConnection();
        String sql = "SELECT MAX(codigopagamento) FROM pagamento;";
        ResultSet rs = null;
        int codigoPagamento = 0;

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            codigoPagamento = rs.getInt(1);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return codigoPagamento;

    }

    public PagamentoCartao getPagamentoCartao(Integer codigo) throws SQLException {
        PagamentoCartao pagamento = null;
        Connection con = null;

        try {

            con = pool.getConnection();
            PreparedStatement ps;
            String sqlSelect = "select * "
                    + "from pagamentocartao AS pb, pagamento AS p "
                    + "where p.codigopedido = ? "
                    + "and p.codigopagamento = pb.codigopagamento "
                    + "order by p.codigopagamento ASC;";

            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();


            List<PagamentoCartao> resultado = getListaPagamentoCartao(rs);
            if (resultado.size() > 0) {
                pagamento = resultado.get(0);
            }

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return pagamento;
    }

    public PagamentoBoleto getPagamentoBoleto(Integer codigo) throws SQLException {
        PagamentoBoleto pagamento = null;
        Connection con = null;

        try {

            con = pool.getConnection();
            PreparedStatement ps;
            String sqlSelect = "select * "
                    + "from pagamentoboleto AS pb, pagamento AS p "
                    + "where p.codigopedido = ? "
                    + "and p.codigopagamento = pb.codigopagamento "
                    + "order by p.codigopagamento ASC;";

            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();


            List<PagamentoBoleto> resultado = getListaPagamentoBoleto(rs);
            if (resultado.size() > 0) {
                pagamento = resultado.get(0);
            }

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return pagamento;
    }

    private void setTabelaPagamentoCartao(PagamentoCartao pagamento, PreparedStatement ps)
            throws SQLException {

        ps.setObject(1, pagamento.getCodigoPagamento());
        ps.setObject(2, pagamento.getNumeroCartao());
        ps.setObject(3, pagamento.getValidadeCartao());
        //System.out.println("bandeira - " + pagamento.getBandeira().name());
        ps.setObject(4, pagamento.getBandeira().name());
        //System.out.println("bandeira - " + pagamento.getBandeira().name());
        ps.setObject(5, pagamento.getCodigoSeguranca());
        ps.setObject(6, pagamento.getNomeTitular());
        ps.setObject(7, pagamento.getNumeroParcelas());
        ps.setObject(8, pagamento.getValorPacela());

    }

    private void setTabelaPagamentoBoleto(PagamentoBoleto pagamento, PreparedStatement ps)
            throws SQLException {

        ps.setObject(1, pagamento.getCodigoPagamento());
        ps.setObject(2, pagamento.getNumeroDocumento());
        ps.setObject(3, Utilitario.getCalendarioParaData(pagamento.getVencimento()));
        ps.setObject(4, pagamento.getCedente());

    }

    private void setTabelaPagamento(Pagamento pagamento, PreparedStatement ps)
            throws SQLException {

        ps.setObject(1, pagamento.getPedido().getCodigoPedido());
        ps.setObject(2, pagamento.getValorTotal());

    }

    private List<Pagamento> getListaPagamento(ResultSet rs) throws SQLException {

        List<Pagamento> resultado = new ArrayList<Pagamento>();

        while (rs.next()) {

            Pagamento pagamento = new PagamentoBoleto();

            pagamento.setCodigoPagamento(rs.getInt("codigopagamento"));
            pagamento.getPedido().setCodigoPedido(rs.getInt("codigopedido"));
            pagamento.setValorTotal(rs.getDouble("valortotal"));

            resultado.add(pagamento);

        }

        return resultado;
    }

    private List<PagamentoCartao> getListaPagamentoCartao(ResultSet rs) throws SQLException {

        List<PagamentoCartao> resultado = new ArrayList<PagamentoCartao>();

        while (rs.next()) {

            PagamentoCartao pagamento = new PagamentoCartao();

            pagamento.setCodigoPagamento(rs.getInt("codigopagamento"));
            pagamento.getPedido().setCodigoPedido(rs.getInt("codigopedido"));
            pagamento.setValorTotal(rs.getDouble("valortotal"));
            pagamento.setNumeroCartao(rs.getString("numerocartao"));
            pagamento.setValidadeCartao(rs.getString("validadecartao"));
            pagamento.setBandeira(TipoCartao.valueOf(TipoCartao.class, rs.getString("bandeira")));
            pagamento.setCodigoSeguranca(rs.getString("codigoseguranca"));
            pagamento.setNomeTitular(rs.getString("nometitular"));
            pagamento.setNumeroParcelas(rs.getInt("numeroparcelas"));
            pagamento.setValorPacela(rs.getDouble("valorparcela"));

            resultado.add(pagamento);
        }

        return resultado;
    }

    private List<PagamentoBoleto> getListaPagamentoBoleto(ResultSet rs) throws SQLException {

        List<PagamentoBoleto> resultado = new ArrayList<PagamentoBoleto>();

        while (rs.next()) {

            PagamentoBoleto pagamento = new PagamentoBoleto();

            pagamento.setCodigoPagamento(rs.getInt("codigopagamento"));
            pagamento.getPedido().setCodigoPedido(rs.getInt("codigopedido"));
            pagamento.setValorTotal(rs.getDouble("valortotal"));
            pagamento.setNumeroDocumento(rs.getString("numerodocumento"));
            pagamento.setVencimento(Utilitario.getDataParaCalendario(rs.getDate("vencimento")));
            pagamento.setCedente(rs.getString("cedente"));

            resultado.add(pagamento);
        }

        return resultado;
    }
}
