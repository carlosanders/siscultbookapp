/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Entrega;
import br.com.siscultbook.bean.PagamentoBoleto;
import br.com.siscultbook.bean.PagamentoCartao;
import br.com.siscultbook.bean.Pedido;
import br.com.siscultbook.model.dao.EntregaDAO;
import br.com.siscultbook.model.dao.PagamentoDAO;
import br.com.siscultbook.model.dao.PedidoDAO;
import br.com.siscultbook.util.Utilitario;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class EditarPedido implements InterfaceCommand {

    private PedidoDAO pedidoDAO;
    private PagamentoDAO pagamentoDAO;
    private EntregaDAO entregaDAO;

    public EditarPedido(PedidoDAO pedidoDAO, PagamentoDAO pagamentoDAO, EntregaDAO entregaDAO) {
        super();
        this.pedidoDAO = pedidoDAO;
        this.pagamentoDAO = pagamentoDAO;
        this.entregaDAO = entregaDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String codigo;
        codigo = request.getParameter("codigo");
        String titulo = "";

        try {

            if (codigo == null) {
                request.setAttribute("pedidos", pedidoDAO.buscarTodosOsPedidos());
                request.setAttribute("titulo", "Consulta de Pedidos");
                request.setAttribute("erro", "Valor do código inválido" + codigo);
                return "restrito/consultaPedido.jsp";
            }

            Pedido pedido = new Pedido();
            Entrega entrega = new Entrega();
            System.out.println("codigo passado - " + codigo);
            request.setAttribute("pedido", pedidoDAO.getPedidoComDadosCliente(Integer.parseInt(codigo)));
            pedido = pedidoDAO.getPedido(Integer.parseInt(codigo));
            entrega = entregaDAO.getEntrega(pedido.getCodigoPedido());
            request.setAttribute("entrega", entregaDAO.getEntrega(pedido.getCodigoPedido()));

            if (pagamentoDAO.getPagamentoCartao(pedido.getCodigoPedido()) != null) {
                PagamentoCartao pagamento = pagamentoDAO.getPagamentoCartao(pedido.getCodigoPedido());
                request.setAttribute("pagamento", pagamento);
                request.setAttribute("valorTotalCarrinho", Utilitario.formatarParaMonetario(pagamento.getValorTotal() - entrega.getFrete()));
                request.setAttribute("pagamentoTotal", Utilitario.formatarParaMoeda(pagamento.getValorTotal()));
                request.setAttribute("pagamentoValorParcela", Utilitario.formatarParaMoeda(pagamento.getValorPacela()));
                request.setAttribute("pagamentoBandeira", pagamento.getBandeira().name());
            } else {
                PagamentoBoleto pagamento = pagamentoDAO.getPagamentoBoleto(pedido.getCodigoPedido());
                request.setAttribute("pagamento", pagamento);
                request.setAttribute("pagamentoTotal", Utilitario.formatarParaMoeda(pagamento.getValorTotal()));
                request.setAttribute("valorTotalCarrinho", Utilitario.formatarParaMonetario(pagamento.getValorTotal() - entrega.getFrete()));
                request.setAttribute("pagamentoBoletoVencimento", Utilitario.DataFormatada(pagamento.getVencimento()));

            }

            request.setAttribute("pedidoStatus", pedido.getStatusPedido().name());
            request.setAttribute("pedidoDataPedido", Utilitario.DataFormatada(pedido.getDataPedido()));
            //request.setAttribute("pedidoValorTotalItem", pedido.);
            request.setAttribute("entrega", entrega);
            request.setAttribute("entregaEstado", entrega.getEndereco().getEstado());
            request.setAttribute("entregaFrete", Utilitario.formatarParaMoeda(entrega.getFrete()));
            request.setAttribute("titulo", "Itens do Pedido Número: " + pedido.getCodigoPedido());


        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor do código inválido" + codigo);
        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com acesso a base de dados: </font>" + e.getMessage());
            e.printStackTrace();
        }

        titulo = titulo + "Atualização - Pedido";
        request.setAttribute("titulo", titulo);


        return "restrito/atualizaPedido.jsp";
    }
}
