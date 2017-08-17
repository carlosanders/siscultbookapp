package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Autor;
import br.com.siscultbook.bean.Estados;
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
public class AutorDAO implements InterfaceAutorDAO {

    private InterfacePool pool;

    public AutorDAO(InterfacePool pool) {
        this.pool = pool;
    }

    public void excluir(Integer codigo) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlExcluir = "DELETE FROM autor WHERE codigoautor = ?";

        try {
            ps = con.prepareStatement(sqlExcluir);
            ps.setInt(1, codigo);
            ps.execute();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
    }

    public void salvar(Autor autor) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlInsert = "INSERT INTO autor (nomecompleto, datanascimento, cpf, "
                + "sexo, rua, bairro, cidade, estado, sobrenome, cep, status)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?,?);";

        try {

            ps = con.prepareCall(sqlInsert);
            setPreparedStatement(autor, ps);

            ps.executeUpdate();
            System.out.println("Debug - " + autor + ps);
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizar(Autor autor) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE autor SET nomecompleto = ?, datanascimento = ?, "
                + "cpf = ?, sexo = ?, rua = ?, bairro = ?, cidade = ?, estado = ?,"
                + "sobrenome = ?, cep = ?, status = ? WHERE codigoautor = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            setPreparedStatement(autor, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(12, autor.getCodigoAutor());

            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public Autor getAutor(Integer codigo) throws SQLException {
        Autor autor = null;
        Connection con = null;

        try {

            con = pool.getConnection();
            PreparedStatement ps;
            String sqlSelect = "SELECT * FROM autor WHERE codigoautor = ?";
            
            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigo);
            ResultSet rs = ps.executeQuery();

            List<Autor> resultado = getListaAutor(rs);
            if (resultado.size() > 0) {
                autor = resultado.get(0);
            }

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return autor;
    }

    public Autor buscarAutorPorCPF(String cpf) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlSelect = "SELECT * FROM autor WHERE cpf = ?;";

        try {
            ps = con.prepareStatement(sqlSelect);
            ps.setString(1, cpf);

            rs = ps.executeQuery();
            //esse if significa que o select retornou um cliente
            List<Autor> resultado = getListaAutor(rs);
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

    public List<Autor> getAutores() throws SQLException {
        List<Autor> resultado = new ArrayList<Autor>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * "
                + "FROM autor AS a "
                + "order by a.codigoautor ASC;";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaAutor(rs);

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public List<Autor> getAutoresAtivos() throws SQLException {
        List<Autor> resultado = new ArrayList<Autor>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * "
                + "FROM autor AS a "
                + "where a.status = 'Ativo'"
                + "order by a.codigoautor ASC;";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaAutor(rs);

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    private void setPreparedStatement(Autor autor, PreparedStatement ps)
            throws SQLException {

        ps.setObject(1, autor.getNomeCompleto());
        ps.setObject(2, autor.getDataNascimentoFormatadaBD());
        ps.setObject(3, autor.getCpf());
        ps.setObject(4, autor.getSexo().name());
        ps.setString(5, autor.getEndereco().getRua());
        ps.setString(6, autor.getEndereco().getBairro());
        ps.setString(7, autor.getEndereco().getCidade());
        ps.setString(8, autor.getEndereco().getEstado().name());
        ps.setString(9, autor.getSobrenome());
        ps.setString(10, autor.getEndereco().getCep());
        ps.setString(11, autor.getStatus().name());

    }

    private List<Autor> getListaAutor(ResultSet rs)
            throws SQLException {
        List<Autor> resultado = new ArrayList<Autor>();

        while (rs.next()) {

            Autor autor = new Autor();

            autor.setCodigoAutor(rs.getInt("codigoautor"));
            autor.setNomeCompleto(rs.getString("nomecompleto"));
            autor.setDataNascimento(Utilitario.getDataParaCalendario(rs.getDate("datanascimento")));
            autor.setCpf(rs.getString("cpf"));
            autor.setSexo(Sexo.valueOf(Sexo.class, rs.getString("sexo")));
            autor.setStatus(Status.valueOf(Status.class, rs.getString("status")));
            //dados do endereço
            autor.getEndereco().setRua(rs.getString("rua"));
            autor.getEndereco().setBairro(rs.getString("bairro"));
            autor.getEndereco().setCidade(rs.getString("cidade"));
            autor.getEndereco().setCep(rs.getString("cep"));
            autor.getEndereco().setEstado(Estados.valueOf(Estados.class, rs.getString("estado")));
            autor.setSobrenome(rs.getString("sobrenome"));

            resultado.add(autor);

        }

        return resultado;
    }
}
