/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Assunto;
import br.com.siscultbook.bean.EstadoCivil;
import br.com.siscultbook.bean.EstadoDoPedido;
import br.com.siscultbook.bean.Estados;
import br.com.siscultbook.bean.Item;
import br.com.siscultbook.bean.Pedido;
import br.com.siscultbook.bean.Status;
import br.com.siscultbook.conexao.InterfacePool;
import br.com.siscultbook.util.Utilitario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class PedidoDAO {

    private InterfacePool pool;

    public PedidoDAO(InterfacePool pool) {
        this.pool = pool;
    }

    public void salvar(Pedido pedido) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlInsertLivro = "INSERT INTO pedido (codigocliente, descricao, "
                + "statuspedido, datapedido)"
                + "VALUES (?,?,?,?);";

        try {

            ps = con.prepareCall(sqlInsertLivro);
            setTabelaPedido(pedido, ps);
            //ps.setInt(9, produto.getCodigoProduto());
            //System.out.println("Debug - " + pedido.getCliente().getCodigoCliente() + " - " + ps);

            ps.executeUpdate();
            ps.close();
            //this.gravarAutores(produto);

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizar(Pedido pedido) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE pedido SET codigocliente = ?, descricao = ?, statuspedido = ?, datapedido = ?"
                + " WHERE codigopedido = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            setTabelaPedido(pedido, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(5, pedido.getCodigoPedido());

            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public List<Pedido> getPedidosDoCliente(Integer codigo) throws SQLException {
        List<Pedido> resultado = new ArrayList<Pedido>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * FROM pedido AS p "
                + "WHERE p.codigocliente = ? "
                + "order by p.codigopedido ASC;";

        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            ps.setInt(1, codigo);
            //System.out.println("comando SQL - " + ps);
            rs = ps.executeQuery();

            resultado = getListaPedido(rs);

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public List<Pedido> buscarTodosOsPedidos() throws SQLException {
        List<Pedido> resultado = new ArrayList<Pedido>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * "
                + "FROM pedido AS p, cliente c "
                + "WHERE p.codigocliente = c.codigocliente "
                + "order by p.codigopedido ASC;";

        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            //System.out.println("comando SQL - " + ps);
            rs = ps.executeQuery();
            resultado = getListaPedidoComCliente(rs);
            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public void buscarPosicaoPedidos() throws SQLException {
        List<Pedido> resultado = new ArrayList<Pedido>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "select COUNT(*) AS total, p.statuspedido "
                + "from pedido AS p "
                + "group by p.statuspedido;";

        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            //System.out.println("comando SQL - " + ps);
            rs = ps.executeQuery();
            resultado = getListaPedidoComCliente(rs);
            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        
    }

    public List<Pedido> buscarTodosOsPedidosEntre(GregorianCalendar dataInicial, GregorianCalendar dataFinal) throws SQLException {
        List<Pedido> resultado = new ArrayList<Pedido>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "select * "
                + "from pedido p, cliente c "
                + "WHERE (p.datapedido BETWEEN ? AND ?) "
                + "and p.codigocliente = c.codigocliente;";

        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            //System.out.println("comando SQL - " + ps);
            ps.setDate(1, Utilitario.getCalendarioParaData(dataInicial));
            ps.setDate(2, Utilitario.getCalendarioParaData(dataFinal));
            System.out.println("comando SQL - " + ps);
            
            rs = ps.executeQuery();
            resultado = getListaPedidoComCliente(rs);
            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public Integer recuperaCodigoPedido() throws SQLException {

        PreparedStatement ps = null;
        Connection con = pool.getConnection();
        String sql = "SELECT MAX(codigopedido) FROM pedido;";
        ResultSet rs = null;
        int codigoPedido = 0;

        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            rs.next();
            codigoPedido = rs.getInt(1);
            //System.out.println("debug recuperaCodigoProduto - " + codigoPedido);
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return codigoPedido;

    }

    public void salvarListaPedidoProduto(Pedido pedido) throws SQLException {

        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlInsert = "INSERT INTO item_pedido (codigopedido, quantidade, codigoproduto) "
                + "VALUES (?, ?, ?);";
        try {
            ps = con.prepareStatement(sqlInsert);
            List<Item> itens = pedido.getItens();


            //System.out.println("comando SQL - " + pedido.getCodigoPedido());
            for (Item item : itens) {

                ps.setInt(1, pedido.getCodigoPedido());
                ps.setInt(2, item.getQuantidade());
                ps.setInt(3, item.getProduto().getCodigoProduto());
                //System.out.println("codigoPedido - " + pedido.getCodigoPedido());
                //System.out.println("item - " + item.getQuantidade()+ " - " + item.getProduto().getCodigoProduto());
                ps.addBatch();
                //System.out.println("comando SQL INSERT LISTA - " + ps);
            }
            ps.executeBatch();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }

    }

    private void setTabelaPedido(Pedido pedido, PreparedStatement ps)
            throws SQLException {

        //ps.setInt(1, produto.getCodigoProduto());
        ps.setInt(1, pedido.getCliente().getCodigoCliente());
        ps.setString(2, pedido.getDescricao());
        ps.setString(3, pedido.getStatusPedido().name());
        ps.setDate(4, Utilitario.getCalendarioParaData(pedido.getDataPedido()));

    }

    private List<Pedido> getListaPedido(ResultSet rs) throws SQLException {

        List<Pedido> resultado = new ArrayList<Pedido>();

        while (rs.next()) {
            Pedido pedido = new Pedido();

            pedido.setCodigoPedido(rs.getInt("codigopedido"));
            pedido.getCliente().setCodigoCliente(rs.getInt("codigocliente"));
            pedido.setDescricao(rs.getString("descricao"));
            pedido.setStatusPedido(EstadoDoPedido.valueOf(EstadoDoPedido.class, rs.getString("statuspedido")));
            pedido.setDataPedido(Utilitario.getDataParaCalendario(rs.getDate("datapedido")));
            resultado.add(pedido);
        }
        return resultado;
    }
	
	private List<Pedido> getListaPedidoComCliente(ResultSet rs) throws SQLException {

        List<Pedido> resultado = new ArrayList<Pedido>();

        while (rs.next()) {
            Pedido pedido = new Pedido();

            pedido.setCodigoPedido(rs.getInt("codigopedido"));

            pedido.getCliente().setCodigoCliente(rs.getInt("codigocliente"));
            pedido.getCliente().setNomeCompleto(rs.getString("nomecompleto"));
            pedido.getCliente().setCpf(rs.getString("cpf"));
            pedido.getCliente().getLoginCliente().setStatus(Status.valueOf(Status.class, rs.getString("status")));
            pedido.getCliente().setCodigoCliente(rs.getInt("codigocliente"));
            pedido.getCliente().setEmail(rs.getString("email"));
            pedido.getCliente().setTelefone(rs.getString("telefone"));
            pedido.getCliente().setDesde(Utilitario.getDataParaCalendario(rs.getDate("desde")));
            pedido.getCliente().setDataNascimento(Utilitario.getDataParaCalendario(rs.getDate("datanascimento")));
            pedido.getCliente().setEstadoCivil(EstadoCivil.valueOf(EstadoCivil.class, rs.getString("estadocivil")));
            pedido.getCliente().getEndereco().setRua(rs.getString("rua"));
            pedido.getCliente().getEndereco().setBairro(rs.getString("bairro"));
            pedido.getCliente().getEndereco().setCidade(rs.getString("cidade"));
            pedido.getCliente().getEndereco().setEstado(Estados.valueOf(Estados.class, rs.getString("estado")));
            pedido.getCliente().getLoginCliente().setSenha(rs.getString("senha"));
            pedido.getCliente().getLoginCliente().setUltimoAcesso(Utilitario.getTimestampParaCalendario(rs.getTimestamp("ultimoacesso")));
            pedido.getCliente().getEndereco().setCep(rs.getString("cep"));
            pedido.getCliente().getLoginCliente().getNivelDeAcesso().setCodigoNivelDeAcesso(rs.getInt("codigoniveldeacesso"));

            pedido.setDescricao(rs.getString("descricao"));
            pedido.setDataPedido(Utilitario.getDataParaCalendario(rs.getDate("datapedido")));
            pedido.setStatusPedido(EstadoDoPedido.valueOf(EstadoDoPedido.class, rs.getString("statuspedido")));

            resultado.add(pedido);
        }
        return resultado;
    }

    private List<Item> getListaItens(ResultSet rs) throws SQLException {

        List<Item> resultado = new ArrayList<Item>();

        while (rs.next()) {
            Item item = new Item();

            item.getProduto().setCodigoProduto(rs.getInt("codigoproduto"));
            item.getProduto().setTitulo(rs.getString("titulo"));
            item.getProduto().setIsbn(rs.getString("isbn"));
            item.getProduto().getEditora().setCodigoEditora(rs.getInt("codigoeditora"));
            item.getProduto().setEstoque(rs.getInt("estoque"));
            item.getProduto().setAssunto(Assunto.valueOf(Assunto.class, rs.getString("assunto")));
            item.getProduto().setFigura(rs.getString("figura"));
            item.getProduto().setAnoPublicacao(rs.getString("anopublicacao"));
            item.getProduto().setPaginaPrincipal(rs.getString("pagprincipal"));
            item.setQuantidade(rs.getInt("quantidade"));
            item.getProduto().setDescricaoCurta(rs.getString("descricaocurta"));
            item.getProduto().setDescricaoLonga(rs.getString("descricaolonga"));
            item.getProduto().setPreco(rs.getDouble("preco"));
            
            
            resultado.add(item);
        }

        return resultado;
    }
    
    public Pedido getPedido(Integer codigo) throws SQLException {
        Pedido pedido = null;
        Connection con = null;

        try {

            con = pool.getConnection();
            PreparedStatement ps;
            String sqlSelect = "select * from pedido AS p "
                    + "where p.codigopedido = ? "
                    + "order by p.codigopedido ASC;";

            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();


            List<Pedido> resultado = getListaPedido(rs);
            if (resultado.size() > 0) {
                pedido = resultado.get(0);
            }

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return pedido;
    }
	
	public Pedido getPedidoComDadosCliente(Integer codigo) throws SQLException {
        Pedido pedido = null;
        Connection con = null;

        try {

            con = pool.getConnection();
            PreparedStatement ps;
            String sqlSelect = "select * "
                    + "from pedido AS p, cliente AS c "
                    + "where p.codigopedido = ? "
                    + "and p.codigocliente = c.codigocliente "
                    + "order by p.codigopedido ASC;";

            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();


            List<Pedido> resultado = getListaPedidoComCliente(rs);
            if (resultado.size() > 0) {
                pedido = resultado.get(0);
            }

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return pedido;
    }

    public List<Item> lerItens(Integer codigoProduto) throws SQLException {
        String sql = "select * "
                + "from pedido AS p, item_pedido AS it, livro AS li, produto AS pr "
                + "where p.codigopedido = it.codigopedido "
                + "and li.codigoproduto = it.codigoproduto "
                + "and pr.codigoproduto = it.codigoproduto "
                + "and  p.codigopedido = ?;";

        List<Item> resultado = new ArrayList<Item>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoProduto);
            rs = ps.executeQuery();

            resultado = getListaItens(rs);

            System.out.println("codigo passadoItens - " + ps);
            //System.out.println("codigo resultado - " + resultado);
            rs.close();
            ps.close();


        } finally {
            pool.liberarConnection(con);
        }
        return resultado;

    }
}
