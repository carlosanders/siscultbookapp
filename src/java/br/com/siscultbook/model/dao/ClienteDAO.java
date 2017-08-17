package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.bean.EstadoCivil;
import br.com.siscultbook.bean.Estados;
import br.com.siscultbook.bean.NivelDeAcesso;
import br.com.siscultbook.bean.Sexo;
import br.com.siscultbook.bean.Status;
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
 * @author Carlos
 */
public class ClienteDAO implements InterfaceClienteDAO {

    private InterfacePool pool;

    public ClienteDAO(InterfacePool pool) {
        this.pool = pool;
    }

    public void excluir(Integer codigo) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlExcluir = "DELETE FROM cliente WHERE codigocliente = ?";

        try {
            ps = con.prepareStatement(sqlExcluir);
            ps.setInt(1, codigo);
            ps.execute();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
    }

    public void salvar(Cliente cliente) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlInsert = "INSERT INTO cliente (email, telefone,"
                + "desde, nomecompleto, datanascimento, cpf, sexo, estadocivil, rua, bairro,"
                + "cidade, estado, senha, codigoniveldeacesso, ultimoacesso,"
                + "status, cep) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,(SELECT NOW()),?,?);";

        try {

            ps = con.prepareCall(sqlInsert);
            setPreparedStatement(cliente, ps);

            ps.executeUpdate();
            //System.out.println("Debug - " + cliente + ps);
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }

    }

    private void setPreparedStatement(Cliente cliente, PreparedStatement ps)
            throws SQLException {

        ps.setObject(1, cliente.getEmail());
        ps.setObject(2, cliente.getTelefone());
        ps.setObject(3, cliente.getDesdeFormatadaParaBD());
        ps.setObject(4, cliente.getNomeCompleto());
        ps.setObject(5, cliente.getDataNascimentoFormatadaBD());
        ps.setString(6, cliente.getCpf());
        ps.setObject(7, cliente.getSexo().name());
        ps.setObject(8, cliente.getEstadoCivil().name());
        ps.setString(9, cliente.getEndereco().getRua());
        ps.setString(10, cliente.getEndereco().getBairro());
        ps.setString(11, cliente.getEndereco().getCidade());
        ps.setString(12, cliente.getEndereco().getEstado().name());

        ps.setString(13, cliente.getLoginCliente().getSenha());
        ps.setObject(14, cliente.getLoginCliente().getNivelDeAcesso().getCodigoNivelDeAcesso());
        //ps.setObject(15, cliente.getLoginCliente().getUltimoAcessoFormatadaParaBD());
        ps.setObject(15, cliente.getLoginCliente().getStatus().name());
        //ps.setInt(18, cliente.getCodigoCliente());
        ps.setString(16, cliente.getEndereco().getCep());

    }

    public void atualizar(Cliente cliente) throws SQLException {

        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE cliente SET email = ?, telefone = ?, desde = ?,"
                + "nomecompleto = ?, datanascimento = ?, cpf = ?, sexo = ?, estadocivil = ?,"
                + "rua = ?, bairro = ?, cidade = ?, estado = ?, senha = ?,"
                + "codigoniveldeacesso = ?, status = ?, cep = ? WHERE codigocliente = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            setPreparedStatement(cliente, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(17, cliente.getCodigoCliente());

            ps.executeUpdate();
            //System.out.println("SQL - " + cliente + ps);
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }

    }

    public void atualizarUltimoAcesso(Integer codigoCliente) throws SQLException {

        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE cliente SET ultimoacesso = (SELECT NOW()) "
                + "WHERE codigocliente = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            //setPreparedStatement(cliente, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(1, codigoCliente);

            ps.executeUpdate();
            //System.out.println("SQL - " + codigoCliente + ps);
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }

    }

    public Cliente getCliente(Integer codigo) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlSelect = "SELECT * "
                + "FROM cliente "
                + "WHERE codigocliente = ?;";

        try {
            ps = con.prepareStatement(sqlSelect);
            ps.setInt(1, codigo);

            rs = ps.executeQuery();
            //esse if significa que o select retornou um cliente
            List<Cliente> resultado = getListaCliente(rs);
            if (resultado.size() > 0) {
                return resultado.get(0);
            }
            rs.close();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }

        return null;
    }

    public Cliente buscarClientePorCPF(String cpf) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlSelect = "SELECT * FROM cliente WHERE cpf = ?;";

        try {
            ps = con.prepareStatement(sqlSelect);
            ps.setString(1, cpf);

            rs = ps.executeQuery();
            //esse if significa que o select retornou um cliente
            List<Cliente> resultado = getListaCliente(rs);
            if (resultado.size() > 0) {
                return resultado.get(0);
            }
            rs.close();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }

        return null;
    }

    public List<Cliente> getClientes() throws SQLException {
        List<Cliente> resultado = new ArrayList<Cliente>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * "
                + "FROM cliente AS c "
                + "order by c.nomecompleto ASC;";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaCliente(rs);

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    private List<Cliente> getListaCliente(ResultSet rs) throws SQLException {

        List<Cliente> resultado = new ArrayList<Cliente>();

        while (rs.next()) {
            Cliente cliente = new Cliente();

            cliente.setCodigoCliente(rs.getInt("codigocliente"));
            cliente.setEmail(rs.getString("email"));
            cliente.setTelefone(rs.getString("telefone"));
            cliente.setDesde(Utilitario.getDataParaCalendario(rs.getDate("desde")));

            cliente.setNomeCompleto(rs.getString("nomecompleto"));
            cliente.setDataNascimento(Utilitario.getDataParaCalendario(rs.getDate("datanascimento")));
            cliente.setCpf(rs.getString("cpf"));
            cliente.setSexo(Sexo.valueOf(Sexo.class, rs.getString("sexo")));
            cliente.setEstadoCivil(EstadoCivil.valueOf(EstadoCivil.class, rs.getString("estadocivil")));
            //dados do endereço
            cliente.getEndereco().setRua(rs.getString("rua"));
            cliente.getEndereco().setBairro(rs.getString("bairro"));
            cliente.getEndereco().setCidade(rs.getString("cidade"));
            cliente.getEndereco().setCep(rs.getString("cep"));
            cliente.getEndereco().setEstado(Estados.valueOf(Estados.class, rs.getString("estado")));
            //dados do usuario

            cliente.getLoginCliente().setSenha(rs.getString("senha"));
            cliente.getLoginCliente().getNivelDeAcesso().setCodigoNivelDeAcesso(rs.getInt("codigoniveldeacesso"));
            cliente.getLoginCliente().setUltimoAcesso(Utilitario.getTimestampParaCalendario(rs.getTimestamp("ultimoacesso")));
            cliente.getLoginCliente().setStatus(Status.valueOf(Status.class, rs.getString("status")));

            resultado.add(cliente);

        }

        return resultado;
    }

    public Cliente getClienteLogin(String cpf) throws SQLException {
        Cliente cliente = null;
        Connection con = null;

        try {

            con = pool.getConnection();
            PreparedStatement ps;
            String sqlSelect = "SELECT * FROM cliente WHERE cpf = ? AND status = 'Ativo';";
            ps = con.prepareCall(sqlSelect);
            ps.setString(1, cpf);
            ResultSet rs = ps.executeQuery();

            List<Cliente> resultado = getListaCliente(rs);
            if (resultado.size() > 0) {
                cliente = resultado.get(0);
            }

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return cliente;
    }


}
