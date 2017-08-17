/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Carrinho;
import br.com.siscultbook.bean.Livro;
import br.com.siscultbook.bean.Produto;
import br.com.siscultbook.model.dao.InterfaceLivroDAO;
import br.com.siscultbook.util.Utilitario;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
public class AdicionarItemNoCarrinho implements InterfaceCommand {

    private InterfaceLivroDAO livroDAO;
    private Integer incrementaNum = 1;

    public AdicionarItemNoCarrinho(InterfaceLivroDAO livroDAO) {
        super();
        this.livroDAO = livroDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        boolean retorna = false;
        String mensagem = "";

        HttpSession session = request.getSession(true);
        Carrinho carrinho;

        try {

            String codigoProduto = null;
            codigoProduto = request.getParameter("itemID");
            if (codigoProduto == null || codigoProduto.equals("")) {
                mensagem += "Código Invalido.<br />";
                retorna = true;
            }

            String quantidade = null;
            quantidade = request.getParameter("quantidade");
            if (quantidade == null || quantidade.equals("")) {
                quantidade = "1";
                retorna = false;
            }

            String cmd = null;
            cmd = request.getParameter("cmd");
            if (cmd == null || cmd.equals("")) {
                mensagem += "Comando Invalido.<br />";
                retorna = true;
            }

            if (retorna) {
                request.setAttribute("mensagem", mensagem);
                return "SisCultbookController?cmd=consultarCatalogo";
            }

            //o objeto session.getAtribute recupero da classe SessionListener.java
            //Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
            synchronized (session) {
                carrinho = (Carrinho) session.getAttribute("carrinho");
                if (carrinho == null) {
                    carrinho = new Carrinho();
                    session.setAttribute("carrinho", carrinho);
                }

                Livro produto = new Livro();
                produto = livroDAO.getLivro(Integer.parseInt(codigoProduto));
                //System.out.println("valorTotal  - " + session.getAttribute("valorCep"));

                
                double valorFrete = 0.00;
                double valorTotal = 0.00;
                if (session.getAttribute("valorCep") != null) {

                    valorFrete = (Double) session.getAttribute("valorCep");
                    valorTotal = +carrinho.calcularTotalCarrinho() + valorFrete;
                    request.setAttribute("valorTotalPedido", Utilitario.formatarParaMonetario(valorTotal));
                    //System.out.println("valorFrete difere de null - " + valorFrete);
                    //System.out.println("valorTotal difere de null - " + valorTotal);

                } else {
                    session.setAttribute("valorCep", valorFrete);
                    valorTotal = valorFrete + carrinho.calcularTotalCarrinho();
                    request.setAttribute("valorTotalPedido", Utilitario.formatarParaMonetario(valorTotal));
                    //System.out.println("valorFrete - " + valorFrete);
                    //System.out.println("valorTotal - " + valorTotal);
                }



                request.setAttribute("titulo", "Estado do Carrinho de Compras");
                //System.out.println("debug valor cep dentro do if:" + session.getAttribute("valorTotalPedido") + session.getAttribute("valorCep"));
                /* if para vrf se eh para add um novo item no carrinho o para
                atualizar a quantidade do item que ja esta no carrinho*/
                if (cmd.equals("adicionarCarrinho")) {
                    request.setAttribute("mensagem", "Item incluído com sucesso: ");
                    //System.out.println("quantidade nova - " + quantidade);
                    carrinho.add(gerarCodigoTemp(), produto, Integer.parseInt(quantidade));

                } else if (cmd.equals("atualizarCarrinho")) {

                    carrinho.setQtdeItens(produto, Integer.parseInt(quantidade));
                    request.setAttribute("mensagem", "Item alterado com sucesso: ");
                    return "SisCultbookController?cmd=consultarItensCarrinho";

                } else if (cmd.equals("removerItemCarrinho")) {

                    //System.out.println("debug removerItemCarrinho - " + codigoProduto);
                    carrinho.remover(Integer.parseInt(codigoProduto));
                    return "SisCultbookController?cmd=consultarItensCarrinho";
                }

                //request.setAttribute("valorCep", carrinho.calcularCEP());

            }


        } catch (SQLException e) {
            request.setAttribute("mensagem", "Problemas com os dados: " + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor inválido: " + e.getMessage());
            e.printStackTrace();
        }

        return "SisCultbookController?cmd=consultarCatalogo";
    }

    //temporario
    private Integer gerarCodigoTemp() {
        return incrementaNum++;
    }
}
