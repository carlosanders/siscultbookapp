package br.com.siscultbook.controller;

import br.com.siscultbook.conexao.InterfacePool;
import br.com.siscultbook.conexao.Pool;
import br.com.siscultbook.model.command.InterfaceCommand;
import br.com.siscultbook.model.helper.SisCultbookHelper;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * O servlet SisCultbookController é responsável pelo gerenciamento de todas as requisições
 * que circularem pela a aplicação web.
 * Ele vai processar essa requisição e delegar para o SisCultbookHelper.java para que ele decida
 * o que fazer com a requisição, depois que SisCultbookHelper.java decidiu o que fazer com a requisição
 * ele vai transferir a execução desse processamento para algum comando.
 * Após o SisCultbookController, vai receber uma página que será a página o destino da requisição.
 */
public class SisCultbookController extends HttpServlet {
    /* O SisCultbookController vai ser responsavel por criar um sisCultbookHelper funcional */
    private InterfacePool pool = Pool.getInstacia();//o sistema pega uma conexao com o banco
    private SisCultbookHelper sisCultbookHelper = new SisCultbookHelper(pool);//repassa a conexao ao helper

    /* Quando o construtor abaixo for criado pegamos o getServletContext() e iremos adicionar o pool
     * no contexto da aplicacao, para que ele fique acessivel a todos os elementos da aplicacao
     */
    public SisCultbookController(){
        super();        
    }

    @Override
    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        this.pool = (InterfacePool)getServletContext().getAttribute("pool");
        //System.out.println("-----Imprimindo o Request---- \n" + getServletContext().getContextPath() + "\n----Fim do Request----");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String dirImagensStr = getInitParameter("dir-imagens");
        request.setAttribute("dirAplicacao", dirImagensStr);
        String dirRelatorios = getInitParameter("dir-relatorios");
        request.setAttribute("dirRelatorios", dirRelatorios);
        request.setAttribute("dirServleContext", getServletContext().getContextPath());        
        RequestDispatcher rd = null;
        sisCultbookHelper.setRequest(request);
        InterfaceCommand comando = sisCultbookHelper.getCommand();
        String pagina = comando.execute(request, response);
        rd = request.getRequestDispatcher(pagina);
        rd.forward(request, response);

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);


    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);


    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";

    }// </editor-fold>
}
