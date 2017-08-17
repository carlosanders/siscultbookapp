package br.com.siscultbook.conexao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Padrao de projeto - Singleton
 * @author Carlos
 */
public class Pool implements InterfacePool {

    private InterfaceDataSource ds;
    private ArrayBlockingQueue<Connection> conexoesLivres;
    private HashMap<String, Connection> conexoesUtilizadas;
    private Integer numeroMaximoConexoes;
    private ResourceBundle config;
    private static Pool instanciaSingleton = null;

    //Construtor privativo - assim nenhum objeto instancia
    private Pool() {
        //localizo qual o pacote e o nome do arquivo de configuracao de conexao
        config = ResourceBundle.getBundle("br.com.siscultbook.conexao.bancodedados");
        ds = new DataSource(config.getString("url"), config.getString("driver"),
                config.getString("usuario"), config.getString("senha"));
        numeroMaximoConexoes = Integer.parseInt(config.getString("numeroMaximoConexoes"));
        conexoesLivres = new ArrayBlockingQueue<Connection>(numeroMaximoConexoes, true);
        conexoesUtilizadas = new HashMap<String, Connection>();
        
    }

    /**
     * Ponto de acesso simples estático e global
     * para os objetos que usaro a conexao com o banco
     * @return uma única instancia de Pool;
     */
    public static synchronized Pool getInstacia() {
        if (instanciaSingleton == null) {
            instanciaSingleton = new Pool();
            System.out.println("nova instancia!");
        }
        return instanciaSingleton;

    }

    public Connection getConnection() {
        Connection con = null;

        try {
            //vrf se o conexoesUtilizadas é menor que numeroMaximoConexao
            if (conexoesUtilizadas.size() < numeroMaximoConexoes) {
                con = conexoesLivres.poll();
                if (con == null) {
                    con = ds.getConnection();
                } else if (con.isClosed()) {
                    this.getConnection();
                }
                /* uma vez entregue a conexao adcionamos ela
                 * ao nosso hashMap de conexoesUtilizadas
                 * identificando pela con.toString() que irá imprimir a conexao
                 * e armazenar em con
                 */
                conexoesUtilizadas.put(con.toString(), con);
            }
        } catch (SQLException e) {
            System.out.println("Problemas com o pool!");
            e.printStackTrace();
        }
        return con;
    }

    public void liberarConnection(Connection con) {
        conexoesLivres.add(con);
        conexoesUtilizadas.remove(con.toString());
    }
}
