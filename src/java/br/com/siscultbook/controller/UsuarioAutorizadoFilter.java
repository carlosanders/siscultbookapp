/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.controller;

import br.com.siscultbook.conexao.InterfacePool;
import br.com.siscultbook.conexao.Pool;
import br.com.siscultbook.model.command.InterfaceCommand;
import br.com.siscultbook.model.command.VerificarAutorizacao;
import br.com.siscultbook.model.dao.NivelDeAcessoDAO;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class UsuarioAutorizadoFilter implements Filter {

    private InterfaceCommand verificarAutorizacao;

    /* Quando instaciamos o filter é criado um objeto verificarAutorizacao que recebe o AcessoDAO como parametro
     * e esse necessita do pool e aí obtemos o pool direto do contexto da aplicacao
     */
    public void init(FilterConfig filterConfig) throws ServletException {
        InterfacePool pool = Pool.getInstacia();
        filterConfig.getServletContext().setAttribute("pool", pool);
        verificarAutorizacao = new VerificarAutorizacao(new NivelDeAcessoDAO(pool));
    }

    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        
        String destino = verificarAutorizacao.execute((HttpServletRequest) request, (HttpServletResponse) response);
        RequestDispatcher dispatcher = null;
        /* Se o destino retornar != null é que vai ser vrf se o usuario tem acesso ao comando ou nao
         * dai retorna ou o comando ou o acessoNegado
         */
        if (destino == null) {
            filterChain.doFilter(request, response);
            //dispatcher = request.getRequestDispatcher("/acessoNegado.jsp");
        //se o resultado do comando VerificarAutoriazacao retornar null prossegue com a aplicacao
        }else{
            //dispatcher = request.getRequestDispatcher(destino);
            ((HttpServletRequest) request).getRequestDispatcher(destino).forward(request, response);

        }
        //filterChain.doFilter(request, response);
        //dispatcher.forward(request, response);
    }

    public void destroy() {
        
    }
}
