/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Acesso;
import br.com.siscultbook.bean.NivelDeAcesso;
import br.com.siscultbook.conexao.InterfacePool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Carlos
 */
public class NivelDeAcessoDAO {

    private InterfacePool pool;

    public NivelDeAcessoDAO(InterfacePool pool) {
        this.pool = pool;
    }

    public void salvarNivelDeAcesso(Acesso nivel) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlInsertAcesso = "INSERT INTO niveldeacesso (niveldeacesso)"
                + "VALUES (?);";

        try {

            ps = con.prepareCall(sqlInsertAcesso);
            ps.setString(1, nivel.getNivelDeAcesso().getNivelDeAcesso());
            ps.executeUpdate();
            ps.close();
            pool.liberarConnection(con);
        } finally {
            pool.liberarConnection(con);
        }
    }

    public void excluirNivelDeAcesso(Integer codigoDoNivel) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlExcluir = "DELETE FROM niveldeacesso WHERE codigoniveldeacesso = ?;";

        try {

            ps = con.prepareStatement(sqlExcluir);
            ps.setInt(1, codigoDoNivel);
            //System.out.println("debug excluir - " + ps);
            ps.execute();
            ps.close();
            pool.liberarConnection(con);
        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizarNivelDeAcesso(Acesso nivel) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE niveldeacesso SET niveldeacesso = ? WHERE codigoniveldeacesso = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);
            ps.setString(1, nivel.getNivelDeAcesso().getNivelDeAcesso());
            ps.setInt(2, nivel.getNivelDeAcesso().getCodigoNivelDeAcesso());

            ps.executeUpdate();
            ps.close();
            pool.liberarConnection(con);

        } finally {
            pool.liberarConnection(con);
        }
    }

    public List<Acesso> retornarListaDeNiveisAcessos() throws SQLException {
        List<Acesso> resultado = new ArrayList<Acesso>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * FROM niveldeacesso;";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaNivelAcesso(rs);

            rs.close();
            ps.close();
            pool.liberarConnection(con);
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public Acesso retornarUmAcesso(Integer codigoDoNivel) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlSelect = "SELECT * FROM niveldeacesso WHERE codigoniveldeacesso = ?;";

        try {
            ps = con.prepareStatement(sqlSelect);
            ps.setInt(1, codigoDoNivel);

            rs = ps.executeQuery();
            //esse if significa que o select retornou um acesso
            List<Acesso> resultado = getListaNivelAcesso(rs);
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

    private List<Acesso> getListaNivelAcesso(ResultSet rs) throws SQLException {

        List<Acesso> resultado = new ArrayList<Acesso>();

        while (rs.next()) {
            Acesso nivel = new Acesso();

            nivel.getNivelDeAcesso().setCodigoNivelDeAcesso(rs.getInt("codigoniveldeacesso"));
            nivel.getNivelDeAcesso().setNivelDeAcesso(rs.getString("niveldeacesso"));

            resultado.add(nivel);

        }

        return resultado;
    }


    // procedimentos para os acessos

    //salva
    public void salvarListaAcessos(List<Acesso> acessos) throws SQLException {

        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlInsert = "INSERT INTO acesso_nivel (codigoniveldeacesso, codigoacesso) "
                + "VALUES (?, ?);";

        try {
            ps = con.prepareStatement(sqlInsert);

            for (Acesso acesso : acessos) {
                
                ps.setInt(1, acesso.getNivelDeAcesso().getCodigoNivelDeAcesso());
                ps.setInt(2, acesso.getCodigoAcesso());
                ps.addBatch();
                //System.out.println("comando SQL INSERT LISTA - " + ps);
            }
            ps.executeBatch();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }

    }

    //excluir todos os acesso do nivel do usuário
    public void excluir(Integer codigoAcesso) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String sqlDelete = "DELETE FROM acesso_nivel WHERE codigoniveldeacesso = ? ;";
            ps = con.prepareStatement(sqlDelete);
            ps.setInt(1, codigoAcesso);

            //System.out.println("comando SQL DELETE - " + ps);
            ps.executeUpdate();
            ps.close();
            pool.liberarConnection(con);

        } finally {
            pool.liberarConnection(con);
        }
    }

    //metodo para retornar os acessos que um nível de acesso tem e os que nao tem tb
    public Map<String, Acesso> getAcessosNivel(Integer codigoAcesso) throws SQLException {

        Map<String, Acesso> resultado = new HashMap<String, Acesso>();
        /* retorna um lista com todos os acesso que o usario tem disponivel e outra
        lista com todos os acessos que o usuario nao tem disponivel */
        String sqlSelect = "SELECT * FROM acesso AS a LEFT JOIN acesso_nivel AS an "
                + "ON a.codigoacesso = an.codigoacesso "
                + "AND an.codigoniveldeacesso = ? "
                + "ORDER BY a.codigoacesso ASC;";
        Connection con = pool.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sqlSelect);
            ps.setInt(1, codigoAcesso);
            //System.out.println("comando SQL - " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Acesso acesso = new Acesso();
                //dado da tabela acesso
                acesso.setCodigoAcesso(rs.getInt("codigoacesso"));
                acesso.setComando(rs.getString("comando"));
                acesso.setDescricao(rs.getString("descricao"));
                //dado da tabela acesso_nivel
                acesso.getNivelDeAcesso().setCodigoNivelDeAcesso(rs.getInt("codigoniveldeacesso"));
                //add no put
                resultado.put(acesso.getComando(), acesso);
            }

            rs.close();
            ps.close();
            pool.liberarConnection(con);
            return resultado;

        } finally {
            pool.liberarConnection(con);
        }

    }

    //metodo para retornar os acessos que um nível de acesso tem sem o acesso cadastrar/excluir/atualizar porque ja vem no comando editar
    public List<Acesso> retornarListaAcessosPorNivel(Integer codigoAcesso) throws SQLException {

        List<Acesso> resultado = new ArrayList<Acesso>();
        /* retorna um lista com todos os acesso que o usario tem disponivel e outra
        lista com todos os acessos que o usuario nao tem disponivel */
        String sqlSelect = "SELECT * FROM acesso AS a JOIN acesso_nivel AS an "
                + "ON a.codigoacesso = an.codigoacesso "
                + "AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'cadastrar%') "
                + "AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'excluir%') "
                + "AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'atualizar%') "
                + "AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'autorizar%') "
                + "AND a.comando not in (Select ac.comando from acesso AS ac WHERE ac.comando LIKE 'exibir%') "
                + "AND an.codigoniveldeacesso = ? "
                + "ORDER BY a.codigoacesso ASC;";
        Connection con = pool.getConnection();
        PreparedStatement ps;
        try {
            ps = con.prepareStatement(sqlSelect);
            ps.setInt(1, codigoAcesso);
            //System.out.println("comando SQL - " + ps);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Acesso acesso = new Acesso();
                //dado da tabela acesso
                acesso.setCodigoAcesso(rs.getInt("codigoacesso"));
                acesso.setComando(rs.getString("comando"));
                acesso.setDescricao(rs.getString("descricao"));
                //dado da tabela acesso_nivel
                acesso.getNivelDeAcesso().setCodigoNivelDeAcesso(rs.getInt("codigoniveldeacesso"));
                //add no put
                resultado.add(acesso);
            }

            rs.close();
            ps.close();

            return resultado;

        } finally {
            pool.liberarConnection(con);
        }

    }

    public List<NivelDeAcesso> getNiveisDeAcesso() throws SQLException {

        List<NivelDeAcesso> resultado = new ArrayList<NivelDeAcesso>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * FROM niveldeacesso";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaNiveisAcesso(rs);

            rs.close();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    private List<NivelDeAcesso> getListaNiveisAcesso(ResultSet rs) throws SQLException {

        List<NivelDeAcesso> resultado = new ArrayList<NivelDeAcesso>();

        while (rs.next()) {
            NivelDeAcesso acesso = new NivelDeAcesso();

            acesso.setCodigoNivelDeAcesso(rs.getInt("codigoniveldeacesso"));
            acesso.setNivelDeAcesso(rs.getString("niveldeacesso"));

            resultado.add(acesso);

        }

        return resultado;
    }

    public List<Acesso> ListaDeAcessos() throws SQLException {

        List<Acesso> resultado = new ArrayList<Acesso>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "SELECT * FROM acesso";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaDeAcessos(rs);

            rs.close();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    private List<Acesso> getListaDeAcessos(ResultSet rs) throws SQLException {

        List<Acesso> resultado = new ArrayList<Acesso>();

        while (rs.next()) {
            Acesso acesso = new Acesso();

            acesso.setCodigoAcesso(rs.getInt("codigoacesso"));
            acesso.setComando(rs.getString("comando"));
            acesso.setDescricao(rs.getString("descricao"));

            resultado.add(acesso);

        }

        return resultado;
    }
}
