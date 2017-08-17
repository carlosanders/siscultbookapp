package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Carrinho;
import br.com.siscultbook.util.Utilitario;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
public class ConsultarItensDoCarrinho implements InterfaceCommand {

    public ConsultarItensDoCarrinho() {
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

                //calcular cep
                double valorFrete = 0.00;
                double valorTotal = 0.00;
                if (session.getAttribute("valorCep") != null) {
                    
                    valorFrete = (Double) session.getAttribute("valorCep");
                    valorTotal = + carrinho.calcularTotalCarrinho() + valorFrete;
                    request.setAttribute("valorTotalPedido", Utilitario.formatarParaMonetario(valorTotal));
                    
                }else{                    
                    session.setAttribute("valorCep", valorFrete);
                    valorTotal = valorFrete + carrinho.calcularTotalCarrinho();
                    request.setAttribute("valorTotalPedido", Utilitario.formatarParaMonetario(valorTotal));
                }                
            }
        }

        return "estadoCarrinho.jsp";
    }
}
