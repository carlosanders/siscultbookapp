/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Editora;
import br.com.siscultbook.bean.Status;
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
public class EditoraDAO implements InterfaceEditoraDAO {

    private InterfacePool pool;

    public EditoraDAO(InterfacePool pool) {
        this.pool = pool;
    }

    public void excluir(Integer codigo) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlExcluir = "DELETE FROM editora WHERE codigoeditora = ?";

        try {
            ps = con.prepareStatement(sqlExcluir);
            ps.setInt(1, codigo);
            ps.execute();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
    }

    public void salvar(Editora editora) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlInsert = "INSERT INTO editora (nomeeditora, cnpj,"
                + "obs, status) VALUES (?,?,?,?);";

        try {

            ps = con.prepareCall(sqlInsert);
            setPreparedStatement(editora, ps);

            ps.executeUpdate();
            //System.out.println("Debug - " + editora + ps);
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizar(Editora editora) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE editora SET nomeeditora = ?, cnpj = ?, obs = ?, status = ?"
                + " WHERE codigoeditora = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            setPreparedStatement(editora, ps);
            //acrescento o campo que nao coloquei no form de cadastro, pois o banco preenche automatico
            ps.setInt(5, editora.getCodigoEditora());

            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    public Editora getEditora(Integer codigo) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlSelect = "SELECT * FROM editora WHERE codigoeditora = ?;";

        try {
            ps = con.prepareStatement(sqlSelect);
            ps.setInt(1, codigo);

            rs = ps.executeQuery();
            //esse if significa que o select retornou um cliente
            List<Editora> resultado = getListaEditora(rs);
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

    public Editora buscarEditoraPorCNPJ(String cnpj) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlSelect = "SELECT * FROM editora WHERE cnpj = ?;";

        try {
            ps = con.prepareStatement(sqlSelect);
            ps.setString(1, cnpj);

            rs = ps.executeQuery();
            //esse if significa que o select retornou um cliente
            List<Editora> resultado = getListaEditora(rs);
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

    public List<Editora> getEditoras() throws SQLException {
        List<Editora> resultado = new ArrayList<Editora>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * "
                + "FROM editora AS e "
                + "order by e.codigoeditora DESC;";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaEditora(rs);

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public List<Editora> getEditorasAtivas() throws SQLException {
        List<Editora> resultado = new ArrayList<Editora>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * "
                + "FROM editora AS e "
                + "where e.status = 'Ativo'"
                + "order by e.codigoeditora DESC;";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaEditora(rs);

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    private void setPreparedStatement(Editora editora, PreparedStatement ps)
            throws SQLException {

        ps.setObject(1, editora.getNomeEditora());
        ps.setObject(2, editora.getCnpj());
        ps.setObject(3, editora.getObs());
        ps.setObject(4, editora.getStatus().name());

    }

    private List<Editora> getListaEditora(ResultSet rs) throws SQLException {
        List<Editora> resultado = new ArrayList<Editora>();

        while (rs.next()) {
            Editora editora = new Editora();

            editora.setCodigoEditora(rs.getInt("codigoeditora"));
            editora.setNomeEditora(rs.getString("nomeeditora"));
            editora.setCnpj(rs.getString("cnpj"));
            editora.setObs(rs.getString("obs"));
            editora.setStatus(Status.valueOf(Status.class, rs.getString("status")));

            resultado.add(editora);

        }

        return resultado;
    }
}
