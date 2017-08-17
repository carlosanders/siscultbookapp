package br.com.siscultbook.conexao;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Essa interface vai definir como vamos obter as conexões
 * utilizando o método getConnection()
 */
public interface InterfaceDataSource {

    public abstract Connection getConnection() throws SQLException;
}
