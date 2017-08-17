
package br.com.siscultbook.bean;

/**
 *
 * @author Carlos
 */
public abstract class Pagamento {

    private Integer codigoPagamento;
    private double valorTotal;
    private Pedido pedido;

    public Pagamento(){
        this.pedido = new Pedido();
    }

    public Integer getCodigoPagamento() {
        return codigoPagamento;
    }

    public void setCodigoPagamento(Integer codigoPagamento) {
        this.codigoPagamento = codigoPagamento;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

}
