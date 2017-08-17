package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Assunto;
import br.com.siscultbook.bean.Autor;
import br.com.siscultbook.bean.Estados;
import br.com.siscultbook.bean.Livro;
import br.com.siscultbook.bean.Produto;
import br.com.siscultbook.bean.Sexo;
import br.com.siscultbook.bean.Status;
import br.com.siscultbook.conexao.InterfacePool;
import br.com.siscultbook.util.Utilitario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Carlos
 */
public class LivroDAO implements InterfaceLivroDAO {

    private InterfacePool pool;

    public LivroDAO(InterfacePool pool) {
        this.pool = pool;
    }

    public void excluir(Integer codigo) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlExcluir = "DELETE FROM livro WHERE codigoproduto = ?;";

        try {

            ps = con.prepareStatement(sqlExcluir);
            ps.setInt(1, codigo);
            //System.out.println("debug excluir - " + ps);
            ps.execute();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
    }

    public void salvar(Livro produto) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;

        String sqlInsertLivro = "INSERT INTO livro (isbn, titulo, "
                + "estoque, assunto, figura, anopublicacao, pagprincipal, codigoeditora, status, codigoproduto)"
                + "VALUES (?,?,?,?,?,?,?,?,?,?);";

        try {

            ps = con.prepareCall(sqlInsertLivro);
            setTabelaLivro(produto, ps);
            ps.setInt(10, produto.getCodigoProduto());
            ps.executeUpdate();
            ps.close();
            this.gravarAutores(produto);

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizar(Livro produto) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE livro SET isbn = ?, titulo = ?, estoque = ?,"
                + "assunto = ?, figura = ?, anopublicacao = ?, pagprincipal= ?, codigoeditora = ?, status = ?"
                + " WHERE codigoproduto = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);

            setTabelaLivro(produto, ps);
            ps.setInt(10, produto.getCodigoProduto());

            ps.executeUpdate();
            ps.close();

            this.gravarAutores(produto);

        } finally {
            pool.liberarConnection(con);
        }
    }

    public void atualizarFigura(Integer codigoProduto) throws SQLException {

        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlAtualizar = "UPDATE livro SET figura = NULL "
                + "where codigoproduto = ?;";
        try {
            ps = con.prepareCall(sqlAtualizar);
            ps.setInt(1, codigoProduto);
            ps.executeUpdate();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }

    }

    public Livro getLivro(Integer codigo) throws SQLException {
        Livro livro = null;
        Connection con = null;

        try {

            con = pool.getConnection();
            PreparedStatement ps;
            String sqlSelect = "select * "
                    + "from livro l, produto p, editora e "
                    + "where l.codigoproduto = ? "
                    + "and p.codigoproduto = ? "
                    + "and l.codigoeditora = e.codigoeditora;";

            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigo);
            ps.setInt(2, codigo);
            ResultSet rs = ps.executeQuery();


            List<Livro> resultado = getListaLivro(rs);
            if (resultado.size() > 0) {
                livro = resultado.get(0);
            }

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return livro;
    }

    public Livro buscarLivroPorISBN(String isbn) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sqlSelect = "SELECT * FROM livro WHERE isbn = ?;";

        try {
            ps = con.prepareStatement(sqlSelect);
            ps.setString(1, isbn);

            rs = ps.executeQuery();
            //esse if significa que o select retornou um cliente
            List<Livro> resultado = ListaLivro(rs);
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

    public List<Livro> getLivros() throws SQLException {
        List<Livro> resultado = new ArrayList<Livro>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "select * "
                + "from livro l, produto p, editora e "
                + "where l.codigoproduto = p.codigoproduto "
                + "and l.codigoeditora = e.codigoeditora "
                + "order by l.titulo ASC;";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaLivro(rs);

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public List<Livro> getLivrosPorPaginas(Integer limite, Integer pagina) throws SQLException {
        List<Livro> resultado = new ArrayList<Livro>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "select * "
                + "from livro l, produto p, editora e "
                + "where l.codigoproduto = p.codigoproduto "
                + "and l.codigoeditora = e.codigoeditora "
                + "order by l.titulo ASC "
                + "LIMIT ? "
                + "OFFSET(? - 1) * ?;";
        ResultSet rs = null;

        try {
            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, limite);
            ps.setInt(2, pagina);
            ps.setInt(3, limite);
            //ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaLivro(rs);

            rs.close();
            //System.out.println("codigo passado - " + ps);
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public List<Livro> getLivrosPorEditora(Integer codigoEditora) throws SQLException {
        List<Livro> resultado = new ArrayList<Livro>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "select * "
                + "from livro AS l, produto AS p, editora AS e "
                + "where p.codigoproduto = l.codigoproduto "
                + "and l.codigoeditora = e.codigoeditora "
                + "and l.codigoeditora = ? "
                + "order by l.codigoproduto ASC;";
        ResultSet rs = null;

        try {
            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigoEditora);
            //ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaLivro(rs);

            rs.close();
            //System.out.println("codigo passado - " + ps);
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public List<Livro> getLivrosPorAssunto(Assunto assunto) throws SQLException {
        List<Livro> resultado = new ArrayList<Livro>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "select * "
                + "from livro AS l, produto AS p, editora AS e "
                + "where p.codigoproduto = l.codigoproduto "
                + "and l.codigoeditora = e.codigoeditora "
                + "and l.assunto = ? "
                + "order by l.codigoproduto ASC;";

        ResultSet rs = null;

        try {

            ps = con.prepareCall(sqlSelect);
            ps.setString(1, assunto.name());
            //ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaLivro(rs);

            rs.close();
            //System.out.println("codigo passado - " + ps);
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public List<Livro> getLivrosPorISBN(String isbn) throws SQLException {
        List<Livro> resultado = new ArrayList<Livro>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "select * "
                + "from livro AS l, produto AS p, editora AS e "
                + "where p.codigoproduto = l.codigoproduto "
                + "and l.codigoeditora = e.codigoeditora "
                + "and UPPER(l.isbn) LIKE UPPER('%" + isbn + "%') "
                + "order by l.codigoproduto ASC;";

        ResultSet rs = null;

        try {

            ps = con.prepareCall(sqlSelect);
            //System.out.println("codigo passado - " + ps);
            rs = ps.executeQuery();
            resultado = getListaLivro(rs);
            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public List<Livro> getLivrosPorTitulo(String titulo) throws SQLException {
        List<Livro> resultado = new ArrayList<Livro>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "select * "
                + "from livro AS l, produto AS p, editora AS e "
                + "where p.codigoproduto = l.codigoproduto "
                + "and l.codigoeditora = e.codigoeditora "
                + "and UPPER(l.titulo) LIKE UPPER('%" + titulo + "%') "
                + "order by l.codigoproduto ASC;";

        ResultSet rs = null;

        try {

            ps = con.prepareCall(sqlSelect);
            rs = ps.executeQuery();
            resultado = getListaLivro(rs);
            rs.close();
            //System.out.println("codigo passado - " + ps);
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public List<Livro> getLivrosPorAutor(Integer codigoAutor) throws SQLException {
        List<Livro> resultado = new ArrayList<Livro>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "select * "
                + "from livro AS l, livro_autor AS la, produto AS p, editora AS e "
                + "where l.codigoproduto = la.codigoproduto "
                + "and p.codigoproduto = la.codigoproduto "
                + "and l.codigoeditora = e.codigoeditora "
                + "and la.codigoautor = ? "
                + "order by l.codigoproduto ASC;";
        ResultSet rs = null;

        try {
            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigoAutor);
            //ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaLivro(rs);

            rs.close();
            //System.out.println("codigo passado - " + ps);
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public Integer totalDeLivrosDoAutor(Integer codigoAutor) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer resultado = null;
        String sqlSelect = "SELECT COUNT(*) AS total "
                + "from livro l, autor a, livro_autor la "
                + "where l.codigoproduto = la.codigoproduto "
                + "and a.codigoautor = la.codigoautor "
                + "and a.codigoautor = ?;";

        try {
            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigoAutor);            
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
            //System.out.println("debug total livros do autor - " + resultado);
            rs.close();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public Integer totalDeLivrosDaEditora(Integer codigoEditora) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer resultado = null;
        String sqlSelect = "SELECT COUNT(*) AS total "
                + "from livro l, editora e "
                + "where l.codigoeditora = e.codigoeditora "
                + "and e.codigoeditora = ?;";

        try {
            ps = con.prepareCall(sqlSelect);
            ps.setInt(1, codigoEditora);
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
            //System.out.println("debug total livros do autor - " + resultado);
            rs.close();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public Integer totalDeLivrosDoAssunto(Assunto nomeAssunto) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer resultado = null;
        String sqlSelect = "SELECT COUNT(*) AS total "
                + "from livro AS l "
                + "where l.assunto = ?;";

        try {
            ps = con.prepareCall(sqlSelect);
            ps.setString(1, nomeAssunto.name());
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
            System.out.println("debug total livros do assnto - " + resultado);
            rs.close();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public Integer totalDeLinhas() throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        Integer resultado = null;
        String sqlSelect = "select count(*) from livro;";

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();
            rs.next();
            resultado = rs.getInt(1);
            //System.out.println("debug recuperaCodigoProduto - " + resultado);
            rs.close();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public List<Livro> getLivrosPaginaPrincipal() throws SQLException {
        List<Livro> resultado = new ArrayList<Livro>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        String sqlSelect = "select * "
                + "from livro l, produto p, editora e "
                + "where l.codigoproduto = p.codigoproduto "
                + "and l.codigoeditora = e.codigoeditora "
                + "and pagprincipal = 'sim';";
        ResultSet rs = null;

        try {
            ps = con.prepareStatement(sqlSelect);
            rs = ps.executeQuery();

            resultado = getListaLivro(rs);

            rs.close();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
        return resultado;
    }

    public List<Autor> lerAutores(Integer codigoProduto) throws SQLException {
        String sql = "select * from "
                + "autor a, livro_autor la "
                + "where la.codigoproduto = ? "
                + "and a.codigoautor = la.codigoautor;";
        List<Autor> resultado = new ArrayList<Autor>();
        Connection con = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, codigoProduto);
            rs = ps.executeQuery();

            resultado = getListaAutores(rs);

            //System.out.println("codigo passado - " + ps);
            //System.out.println("codigo resultado - " + resultado);
            rs.close();
            ps.close();


        } finally {
            pool.liberarConnection(con);
        }
        return resultado;

    }

    public void excluirAutores(Integer codigo) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps;
        String sql = "DELETE FROM livro_autor WHERE codigoproduto = ?";

        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, codigo);
            //System.out.println("debug excluirAutores - " + ps);
            ps.execute();
            ps.close();

        } finally {
            pool.liberarConnection(con);
        }
    }

    private void setTabelaLivro(Livro produto, PreparedStatement ps)
            throws SQLException {

        //ps.setInt(1, produto.getCodigoProduto());
        ps.setString(1, produto.getIsbn());
        ps.setString(2, produto.getTitulo());
        ps.setInt(3, produto.getEstoque());
        ps.setString(4, produto.getAssunto().name());
        ps.setObject(5, produto.getFigura());
        ps.setString(6, produto.getAnoPublicacao());
        ps.setString(7, produto.getPaginaPrincipal());
        ps.setInt(8, produto.getEditora().getCodigoEditora());
        ps.setString(9, produto.getStatus().name());


    }

    private void gravarAutores(Livro produto) {
        Connection con = pool.getConnection();
        PreparedStatement ps;
        String sql = "INSERT INTO livro_autor (codigoproduto, codigoautor) VALUES (?, ?)";

        try {
            ps = con.prepareStatement(sql);

            Set autores = produto.getAutores();
            //System.out.println("debug Qtd autores - " + autores.size());
            Iterator iterator = autores.iterator();

            ps.setInt(1, produto.getCodigoProduto());

            for (int i = 0; i < autores.size(); i++) {

                //System.out.println("debug Interator - " + autores.toString());
                String txt = (String) iterator.next();
                ps.setInt(2, Integer.parseInt(txt));
                ps.executeUpdate();
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //excluir todos os acesso do nivel do usuário
    public void excluirListaAutoresDoLivro(Integer codigoProduto) throws SQLException {
        Connection con = pool.getConnection();
        PreparedStatement ps = null;

        try {
            String sqlDelete = "DELETE FROM livro_autor WHERE codigoproduto = ? ;";
            ps = con.prepareStatement(sqlDelete);
            ps.setInt(1, codigoProduto);

            //System.out.println("comando SQL DELETE - " + ps);
            ps.executeUpdate();
            ps.close();
        } finally {
            pool.liberarConnection(con);
        }
    }

    private void atualizarAutores(Livro produto) {
        Connection con = pool.getConnection();
        PreparedStatement ps;
        String sql = "UPDATE livro_autor SET codigoautor = ?"
                + "WHERE codigoproduto = ?";

        try {
            ps = con.prepareStatement(sql);

            Set autores = produto.getAutores();
            //System.out.println("debug Qtd autores selecionados em atualizarAutores- " + autores.size());
            //System.out.println("debug linha SQL - " + ps);
            Iterator iterator = autores.iterator();

            ps.setInt(2, produto.getCodigoProduto());

            for (int i = 0; i < autores.size(); i++) {

                //System.out.println("debug Interator - " + autores.toString());
                String txt = (String) iterator.next();
                ps.setInt(1, Integer.parseInt(txt));
                ps.executeUpdate();
                //System.out.println("debug linha SQL 1- " + ps);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private List<Livro> getListaLivro(ResultSet rs) throws SQLException {

        List<Livro> resultado = new ArrayList<Livro>();

        while (rs.next()) {
            Livro produto = new Livro();

            produto.setCodigoProduto(rs.getInt("codigoproduto"));
            produto.setIsbn(rs.getString("isbn"));
            produto.setTitulo(rs.getString("titulo"));
            produto.setEstoque(rs.getInt("estoque"));
            produto.setAssunto(Assunto.valueOf(Assunto.class, rs.getString("assunto")));
            produto.setFigura(rs.getString("figura"));
            produto.setAnoPublicacao(rs.getString("anopublicacao"));
            produto.setPaginaPrincipal(rs.getString("pagprincipal"));
            produto.setStatus(Status.valueOf(Status.class, rs.getString("status")));
            //dados editora
            produto.getEditora().setCodigoEditora(rs.getInt("codigoeditora"));
            produto.getEditora().setNomeEditora(rs.getString("nomeeditora"));
            produto.getEditora().setCnpj(rs.getString("cnpj"));
            produto.getEditora().setObs(rs.getString("obs"));
            //dados do produto
            produto.setDescricaoCurta(rs.getString("descricaocurta"));
            produto.setDescricaoLonga(rs.getString("descricaolonga"));
            produto.setPreco(rs.getDouble("preco"));

            resultado.add(produto);

        }

        return resultado;
    }

    private List<Livro> ListaLivro(ResultSet rs) throws SQLException {

        List<Livro> resultado = new ArrayList<Livro>();

        while (rs.next()) {
            Livro produto = new Livro();

            produto.setCodigoProduto(rs.getInt("codigoproduto"));
            produto.setIsbn(rs.getString("isbn"));
            produto.setTitulo(rs.getString("titulo"));
            produto.setEstoque(rs.getInt("estoque"));
            produto.setAssunto(Assunto.valueOf(Assunto.class, rs.getString("assunto")));
            produto.setFigura(rs.getString("figura"));
            produto.setAnoPublicacao(rs.getString("anopublicacao"));
            produto.setPaginaPrincipal(rs.getString("pagprincipal"));
            produto.setStatus(Status.valueOf(Status.class, rs.getString("status")));

            resultado.add(produto);

        }

        return resultado;
    }

    private List<Autor> getListaAutores(ResultSet rs) throws SQLException {

        List<Autor> resultado = new ArrayList<Autor>();

        while (rs.next()) {
            Autor autor = new Autor();

            autor.setCodigoAutor(rs.getInt("codigoautor"));
            autor.setNomeCompleto(rs.getString("nomecompleto"));
            autor.setDataNascimento(Utilitario.getDataParaCalendario(rs.getDate("datanascimento")));
            autor.setCpf(rs.getString("cpf"));
            autor.setSexo(Sexo.valueOf(Sexo.class, rs.getString("sexo")));
            autor.getEndereco().setRua(rs.getString("rua"));
            autor.getEndereco().setBairro(rs.getString("bairro"));
            autor.getEndereco().setCidade(rs.getString("cidade"));
            autor.getEndereco().setEstado(Estados.valueOf(Estados.class, rs.getString("estado")));
            autor.setSobrenome(rs.getString("sobrenome"));
            resultado.add(autor);

        }

        return resultado;
    }

   
    
}
