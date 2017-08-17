/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.bean;

import java.util.List;

/**
 *
 * @author Carlos
 */
public interface InterfaceCarrinho {

    public void add(Integer itemID, Livro produto, Integer quantidade);

    public void setQtdeItens(Livro produto, Integer quantidade);

    public List<Item> buscarItens();

    public double calcularTotalCarrinho();

    public void remover(Integer codigoProduto);

    public Pedido confirmarPedido(Cliente cliente, Pagamento pagamento);

    public Item buscarItem(Integer codigoItem);

    public void clear();
}
