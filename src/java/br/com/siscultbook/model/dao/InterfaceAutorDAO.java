/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Autor;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface InterfaceAutorDAO {

    public void excluir(Integer codigo) throws SQLException;

    public void salvar(Autor autor) throws SQLException;

    public void atualizar(Autor autor) throws SQLException;

    public Autor getAutor(Integer codigo) throws SQLException;

    public Autor buscarAutorPorCPF(String cpf) throws SQLException;

    public List<Autor> getAutores() throws SQLException;
    public List<Autor> getAutoresAtivos() throws SQLException;
}
