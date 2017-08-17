package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Carrinho;
import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.util.Utilitario;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
public class FinalizarPedido implements InterfaceCommand {

    public FinalizarPedido() {
        super();
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(true);
        Carrinho carrinho;
        Cliente cliente;
        try {

            synchronized (session) {
                carrinho = (Carrinho) session.getAttribute("carrinho");
                //vrefica se existe um carrinho na sessao
                if (carrinho == null) {
                    //System.out.println("passou pelo if do carrinho:" + session.getAttribute("carrinho"));
                    return "SisCultbookController?cmd=consultarCatalogo";
                }
                //verifica se existe um usuario na sessao
                if (session.getAttribute("usuario") != null) {
                    //verifica se o usuario da sessao é um Cliente
                    if (session.getAttribute("usuario") instanceof Cliente) {
                        cliente = (Cliente) session.getAttribute("usuario");
                        request.setAttribute("itensCarrinho", carrinho.buscarItens());
                        request.setAttribute("valorTotalCarrinho", Utilitario.formatarParaMonetario(carrinho.calcularTotalCarrinho()));
                        double total = carrinho.calcularTotalCarrinho() + (Double)session.getAttribute("valorCep");
                        request.setAttribute("valorTotalPedido", Utilitario.formatarParaMonetario(total));
                        request.setAttribute("valorTotalPedidoSemFormatacao", total);
                        request.setAttribute("cliente", cliente);
                        request.setAttribute("clienteEstado", cliente.getEndereco().getEstado());
                        request.setAttribute("titulo", "Confirmação");
                        //System.out.println("valor carrinho enviado:" + carrinho.calcularTotalCarrinho());
                        //request.setAttribute("arquivo", "finalizando.jsp");
                        return "finalizarPedido.jsp";

                    } else {
                        //senao for retorno para a tela inicial do sistema
                        return "SisCultbookController?cmd=paginaPrincipal";
                    }
                    //System.out.println("debug usuario:" + session.getAttribute("usuario"));
                } else {
                    //System.out.println("Nao tem nada no usuario:" + session.getAttribute("usuario"));

                    request.setAttribute("titulo", "Identificação");
                    request.setAttribute("arquivo", "identificacao.jsp");
                    return "arquivosEstaticos.jsp";

                }


            }
        } catch (Exception e) {
        }
        return "index.jsp";
    }
}
