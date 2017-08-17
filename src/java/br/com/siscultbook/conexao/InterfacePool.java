package br.com.siscultbook.conexao;

import java.sql.Connection;

/**
 *
 * @author Carlos
 */
public interface InterfacePool {

    public abstract Connection getConnection();

    public void liberarConnection(Connection con);
}
