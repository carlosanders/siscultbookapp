package br.com.siscultbook.bean;

import br.com.siscultbook.util.Utilitario;
import java.sql.Date;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

/*
 * Created on Feb 12, 2004
 *
 * To change the template for this generated file go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
/**
 * @author Fred
 *
 * To change the template for this generated type comment go to
 * Window>Preferences>Java>Code Generation>Code and Comments
 */
public class Pedido {

    private Integer codigoPedido;
    private String descricao;
    private EstadoDoPedido statusPedido;
    private Cliente cliente;
    private List<Item> itens;
    private GregorianCalendar dataPedido;
    

    public Pedido() {
        this.cliente = new Cliente();
        this.itens = new ArrayList<Item>();
    }

    public Pedido(Integer codigoPedido, Cliente cliente) {
        this.codigoPedido = codigoPedido;
        this.cliente = cliente;
        itens = new ArrayList<Item>();
        this.statusPedido = EstadoDoPedido.Aberto;

    }

    public Pedido(Cliente cliente) {
        this(0, cliente);
    }

    public void adicionarItem(Produto produto, Integer quantidade) {
        Item item = new Item(quantidade, produto);

        getItens().add(item);
    }

    public double calcularTotal() {
        double totalPedido = 0.00;

        for (Item item : getItens()) {
            double valorItem = item.calcularSubTotal();
            totalPedido += valorItem;
        }
        return totalPedido;
    }

    public Integer getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Integer codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EstadoDoPedido getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(EstadoDoPedido statusPedido) {
        this.statusPedido = statusPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public GregorianCalendar getDataPedido() {
        return dataPedido;
    }

    public Date getDataPedidoFormatadaBD() {
        return Utilitario.getCalendarioParaData(dataPedido);
    }

    public void setDataPedido(GregorianCalendar dataPedido) {
        this.dataPedido = dataPedido;
    }

}
