/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.dao;

import br.com.siscultbook.bean.Funcionario;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Carlos
 */
public interface InterfaceFuncionarioDAO {

    Funcionario getFuncionario(Integer codigo) throws SQLException;

    Funcionario getFuncionario(String login) throws SQLException;

    void excluir(Integer codigo) throws SQLException;

    void atualizar(Funcionario funcionario) throws SQLException;

    public void atualizarUltimoAcesso(Integer codigoFuncionario) throws SQLException;

    void salvar(Funcionario funcionario) throws SQLException;

    List<Funcionario> getFuncionarios() throws SQLException;
}
