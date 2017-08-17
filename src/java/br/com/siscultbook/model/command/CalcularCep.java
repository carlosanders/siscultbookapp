
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Carrinho;
import br.com.siscultbook.util.Utilitario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author com1dn-711
 */
public class CalcularCep implements InterfaceCommand {

    public CalcularCep() {
        super();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        Carrinho carrinho = (Carrinho) session.getAttribute("carrinho");
        request.setAttribute("titulo", "Estado do Carrinho de Compras");
        request.setAttribute("mensagem", "O carrinho está vazio");

        if (session.getAttribute("carrinho") != null) {
            
            synchronized (session) {
                request.setAttribute("mensagem", "Itens Selecionados");
                request.setAttribute("itensCarrinho", carrinho.buscarItens());
                request.setAttribute("valorTotalCarrinho", Utilitario.formatarParaMonetario(carrinho.calcularTotalCarrinho()));

                /*
                 * Simulacao do cálculo do frete para nnível academico
                 */
                double valorCep = 0.00;

                if (request.getParameter("cep") != null && request.getParameter("cepc") != null) {

                    if (carrinho.buscarItens().size() <= 1) {
                        valorCep = 15.00;
                    } else if (carrinho.buscarItens().size() <= 2) {
                        valorCep = 30.00;
                    } else if (carrinho.buscarItens().size() >= 3) {
                        valorCep = 45.00;
                    }
                }
                double valorPedido = carrinho.calcularTotalCarrinho() + valorCep;
                session.setAttribute("valorCep", valorCep);
                request.setAttribute("valorTotalPedido", Utilitario.formatarParaMonetario(valorPedido));
            }
        }

        return "estadoCarrinho.jsp";
    }

}
