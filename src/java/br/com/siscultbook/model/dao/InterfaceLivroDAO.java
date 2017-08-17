/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Assunto;
import br.com.siscultbook.bean.Autor;
import br.com.siscultbook.bean.Livro;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Carlos
 */
public interface InterfaceLivroDAO {

    public void excluir(Integer codigo) throws SQLException;
    public void salvar(Livro produto) throws SQLException;
    public void atualizar(Livro produto) throws SQLException;
    public void atualizarFigura(Integer codigoProduto) throws SQLException;
    public Livro getLivro(Integer codigo) throws SQLException;
    public List<Livro> getLivros() throws SQLException;
    public List<Livro> getLivrosPorAutor(Integer codigoAutor) throws SQLException;
    public List<Livro> getLivrosPorAssunto(Assunto assunto) throws SQLException;
    public List<Livro> getLivrosPorTitulo(String titulo) throws SQLException;
    public List<Livro> getLivrosPorISBN(String isbn) throws SQLException;
    public List<Livro> getLivrosPorEditora(Integer codigoEditora) throws SQLException;
    public List<Livro> getLivrosPorPaginas(Integer limite, Integer pagina) throws SQLException;
    public Integer totalDeLinhas() throws SQLException;
    public List<Livro> getLivrosPaginaPrincipal() throws SQLException;
    public List<Autor> lerAutores(Integer codigoProduto) throws SQLException;
    public void excluirAutores(Integer codigo) throws SQLException;
    public void excluirListaAutoresDoLivro(Integer codigoProduto) throws SQLException;
    public Livro buscarLivroPorISBN(String isbn) throws SQLException;
    public Integer totalDeLivrosDoAutor(Integer codigoAutor) throws SQLException;
    public Integer totalDeLivrosDaEditora(Integer codigoEditora) throws SQLException;
    public Integer totalDeLivrosDoAssunto(Assunto nomeAssunto) throws SQLException;
}
