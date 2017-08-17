/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Produto;
import br.com.siscultbook.bean.Publicacao;
import java.sql.SQLException;

/**
 *
 * @author home
 */
public interface InterfacePublicacaoDAO {
    public void salvar(Produto produto) throws SQLException;
}
