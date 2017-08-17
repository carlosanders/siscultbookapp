package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Cliente;
import br.com.siscultbook.bean.Entrega;
import br.com.siscultbook.bean.Item;
import br.com.siscultbook.bean.PagamentoBoleto;
import br.com.siscultbook.bean.PagamentoCartao;
import br.com.siscultbook.bean.Pedido;
import br.com.siscultbook.model.dao.ClienteDAO;
import br.com.siscultbook.model.dao.EntregaDAO;
import br.com.siscultbook.model.dao.InterfaceClienteDAO;
import br.com.siscultbook.model.dao.PagamentoDAO;
import br.com.siscultbook.model.dao.PedidoDAO;
import br.com.siscultbook.util.Utilitario;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Carlos
 */
public class ConsultarItensDoPedidoDoCliente implements InterfaceCommand {

    private PedidoDAO pedidoDAO;
    private PagamentoDAO pagamentoDAO;
    private EntregaDAO entregaDAO;
    private InterfaceClienteDAO clienteDAO;

    public ConsultarItensDoPedidoDoCliente(PedidoDAO pedidoDAO, PagamentoDAO pagamentoDAO, EntregaDAO entregaDAO, InterfaceClienteDAO clienteDAO) {
        super();
        this.pedidoDAO = pedidoDAO;
        this.pagamentoDAO = pagamentoDAO;
        this.entregaDAO = entregaDAO;
        this.clienteDAO = clienteDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("titulo", "Itens do Pedido");
        String codigo;
        codigo = request.getParameter("codigoPedido");
        HttpSession session = request.getSession(true);

        try {


            synchronized (session) {

                Pedido pedido = pedidoDAO.getPedido(Integer.parseInt(codigo));
                List<Item> itens = pedidoDAO.lerItens(Integer.parseInt(codigo));
                Cliente cliente = clienteDAO.getCliente(pedido.getCliente().getCodigoCliente());
                request.setAttribute("cliente", cliente);                
                Entrega entrega = entregaDAO.getEntrega(pedido.getCodigoPedido());
                pedido.setItens(itens);

                System.out.println("codigopassadoParaPedido: " + pedido.getCodigoPedido());
                for (Item item : pedido.getItens()) {
                    //System.out.println("pega dados do item: " + item.getQuantidade());
                    //System.out.println("pega dados do item Titulo: " + item.getProduto().getTitulo());
                }
                if (pagamentoDAO.getPagamentoCartao(pedido.getCodigoPedido()) != null) {
                    PagamentoCartao pagamento = pagamentoDAO.getPagamentoCartao(pedido.getCodigoPedido());
                    //System.out.println("pega dados do cartao: " + pagamento.getNumeroCartao());

                    request.setAttribute("pagamento", pagamento);
                    request.setAttribute("valorTotalCarrinho", Utilitario.formatarParaMonetario(pagamento.getValorTotal() - entrega.getFrete()));
                    request.setAttribute("pagamentoTotal", Utilitario.formatarParaMonetario(pagamento.getValorTotal()));
                } else {
                    PagamentoBoleto pagamento = pagamentoDAO.getPagamentoBoleto(pedido.getCodigoPedido());
                    //System.out.println("pega dados do boleto: " + pagamento.getNumeroDocumento());

                    request.setAttribute("pagamento", pagamento);
                    request.setAttribute("pagamentoTotal", Utilitario.formatarParaMonetario(pagamento.getValorTotal()));
                    request.setAttribute("valorTotalCarrinho", Utilitario.formatarParaMonetario(pagamento.getValorTotal() - entrega.getFrete()));
                }

                request.setAttribute("pedido", pedido);
                //request.setAttribute("pedidoValorTotalItem", pedido.);
                request.setAttribute("entrega", entrega);
                request.setAttribute("entregaFrete", Utilitario.formatarParaMonetario(entrega.getFrete()));
                request.setAttribute("titulo", "Itens do Pedido Número: " + pedido.getCodigoPedido());
            }


        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        request.setAttribute("arquivo", "detalhesPedido.jsp");
        return "dadosCliente.jsp";
    }
}
