package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Cargo;
import br.com.siscultbook.bean.Estados;
import br.com.siscultbook.bean.Funcionario;
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
public class FuncionarioDAO implements InterfaceFuncionarioDAO {

    private InterfacePool pool;

    public FuncionarioDAO(InterfacePool pool) {
        super();
        this.pool = pool;

    }

    public Funcionario getFuncionario(Integer codigo) throws SQLException {
        Funcionario funcionario = null;
        Connection con = null;

        try {

            con = pool.getConnection();
            PreparedStatement ps;
            String sqlSelect = "SELECT * FROM funcionario WHERE codigofuncionario = ?;";
            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            List<Funcionario> resultado = getListaFuncionario(rs);
            if (resultado.size() > 0) {
                funcionario = resultado.get(0);
            }
            //System.out.println("SQL1 - " + rs);
            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return funcionario;

    }

    public Funcionario getFuncionario(String login) throws SQLException {
        Funcionario funcionario = null;
        Connection con = null;

        try {

            con = pool.getConnection();
            PreparedStatement ps;
            String sqlSelect = "SELECT * FROM funcionario WHERE cpf = ? AND status = 'Ativo';";
            ps = con.prepareCall(sqlSelect);
            ps.setString(1, login);
            //System.out.println("SQL - " + ps);
            ResultSet rs = ps.executeQuery();

            List<Funcionario> resultado = getListaFuncionario(rs);
            if (resultado.size() > 0) {
                funcionario = resultado.get(0);
            }
            //System.out.println("SQL - " + ps);

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return funcionario;
    }

    public void excluir(Integer codigo) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlExcluir = "DELETE FROM funcionario WHERE codigofuncionario = ?";

        try {
            ps = con.prepareStatement(sqlExcluir);
            ps.setInt(1, codigo);
            ps.execute();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizar(Funcionario funcionario) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE funcionario SET cargo = ?, dataadmissao = ?, datademissao = ?,"
                + "nomecompleto = ?, datanascimento = ?, cpf = ?, sexo = ?,"
                + "rua = ?, bairro = ?, cidade = ?, estado = ?, senha = ?,"
                + "codigoniveldeacesso = ?, status = ?, cep = ? WHERE codigofuncionario = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            setPreparedStatement(funcionario, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(16, funcionario.getCodigoFuncionario());

            ps.executeUpdate();
            //System.out.println("SQL - " + funcionario + ps);
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizarUltimoAcesso(Integer codigoFuncionario) throws SQLException {

        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE funcionario SET ultimoacesso = (SELECT NOW()) "
                + "WHERE codigofuncionario = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            //setPreparedStatement(cliente, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(1, codigoFuncionario);

            ps.executeUpdate();
            //System.out.println("SQL - " + codigoFuncionario + ps);
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }

    }

    private List<Funcionario> getListaFuncionario(ResultSet rs) throws SQLException {

        List<Funcionario> resultado = new ArrayList<Funcionario>();

        while (rs.next()) {

            Funcionario funcionario = new Funcionario();

            funcionario.setCodigoFuncionario(rs.getInt("codigofuncionario"));
            funcionario.setCargo(Cargo.valueOf(Cargo.class, rs.getString("cargo")));
            funcionario.setDataAdmissao(Utilitario.getDataParaCalendario(rs.getDate("dataadmissao")));
            funcionario.setDataDemissao(Utilitario.getDataParaCalendario(rs.getDate("datademissao")));
            funcionario.setNomeCompleto(rs.getString("nomecompleto"));
            funcionario.setDataNascimento(Utilitario.getDataParaCalendario(rs.getDate("datanascimento")));
            funcionario.setCpf(rs.getString("cpf"));
            funcionario.setSexo(Sexo.valueOf(Sexo.class, rs.getString("sexo")));
            //dados do endereço
            funcionario.getEndereco().setRua(rs.getString("rua"));
            funcionario.getEndereco().setBairro(rs.getString("bairro"));
            funcionario.getEndereco().setCidade(rs.getString("cidade"));
            funcionario.getEndereco().setCep(rs.getString("cep"));
            funcionario.getEndereco().setEstado(Estados.valueOf(Estados.class, rs.getString("estado")));
            //dados do usuario
            funcionario.getLoginFuncionario().setSenha(rs.getString("senha"));
            funcionario.getLoginFuncionario().getNivelDeAcesso().setCodigoNivelDeAcesso(rs.getInt("codigoniveldeacesso"));
            funcionario.getLoginFuncionario().setUltimoAcesso(Utilitario.getTimestampParaCalendario(rs.getTimestamp("ultimoacesso")));
            funcionario.getLoginFuncionario().setStatus(Status.valueOf(Status.class, rs.getString("status")));

            resultado.add(funcionario);

        }

        return resultado;

    }

    public void salvar(Funcionario funcionario) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlInsert = "INSERT INTO funcionario (cargo, dataadmissao,"
                + "datademissao, nomecompleto, datanascimento, cpf, sexo, rua, bairro,"
                + "cidade, estado, senha, codigoniveldeacesso, ultimoacesso,"
                + "status, cep) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,(SELECT NOW()),?,?);";

        try {

            ps = con.prepareCall(sqlInsert);
            setPreparedStatement(funcionario, ps);

            ps.executeUpdate();
            //System.out.println("Debug - " + funcionario + ps);
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }

    }

    public List<Funcionario> getFuncionarios() throws SQLException {
        List<Funcionario> resultado = new ArrayList<Funcionario>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * "
                + "FROM funcionario AS f "
                + "where f.codigofuncionario "
                + "not in (Select ff.codigofuncionario from funcionario AS ff where ff.codigofuncionario = 1) "
                + "order by f.nomecompleto ASC;";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaFuncionario(rs);

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    private void setPreparedStatement(Funcionario funcionario,
            PreparedStatement ps) throws SQLException {

        ps.setString(1, funcionario.getCargo().name());
        ps.setObject(2, funcionario.getDataAdmissaoFormatadaBD());
        //System.out.println("dados do obj funcionario - " + funcionario.getDataAdmissaoFormatadaBD());
        ps.setObject(3, funcionario.getDataDemissaoFormatadaBD());
        ps.setString(4, funcionario.getNomeCompleto());
        ps.setObject(5, funcionario.getDataNascimentoFormatadaBD());
        ps.setString(6, funcionario.getCpf());
        ps.setString(7, funcionario.getSexo().name());
        ps.setString(8, funcionario.getEndereco().getRua());
        ps.setString(9, funcionario.getEndereco().getBairro());
        ps.setString(10, funcionario.getEndereco().getCidade());
        ps.setString(11, funcionario.getEndereco().getEstado().name());
        ps.setObject(12, funcionario.getLoginFuncionario().getSenha());
        ps.setObject(13, funcionario.getLoginFuncionario().getNivelDeAcesso().getCodigoNivelDeAcesso());
        //ps.setObject(14, funcionario.getLoginFuncionario().getUltimoAcessoFormatadaParaBD());
        ps.setObject(14, funcionario.getLoginFuncionario().getStatus().name());
        ps.setString(15, funcionario.getEndereco().getCep());

    }
}
