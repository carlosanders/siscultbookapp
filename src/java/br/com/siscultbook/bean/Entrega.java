
package br.com.siscultbook.bean;

/**
 *
 * @author Carlos
 */
public class Entrega extends Cliente{

    private Integer codigoEntrega;
    private double frete;
    private Pedido pedido;

    public Entrega(){
        this.pedido = new Pedido();
    }
   

    public Integer getCodigoEntrega() {
        return codigoEntrega;
    }

    public void setCodigoEntrega(Integer codigoEntrega) {
        this.codigoEntrega = codigoEntrega;
    }

    public double getFrete() {
        return frete;
    }

    public void setFrete(double frete) {
        this.frete = frete;
    }

    /**
     * @return the pedido
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * @param pedido the pedido to set
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }


}
