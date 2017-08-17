package br.com.siscultbook.bean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class Carrinho implements InterfaceCarrinho {

    private List<Item> itemsCarrinho;

    /** Cria um carrinho vazio. */
    public Carrinho() {
        super();
        itemsCarrinho = new ArrayList<Item>();
    }

    /* Verifica se ja existe um item deste no carrinho de compras
    Se existir, aumenta a quantidade dele. Se nao, cria um novo
    item no carrinho de compras
     */
    public synchronized void add(Integer itemID, Livro produto, Integer quantidade) {
        Item item;
        for (int i = 0; i < itemsCarrinho.size(); i++) {
            item = (Item) itemsCarrinho.get(i);
            //System.out.println("item codigo  - " + item.getProduto().getCodigoProduto());
            //System.out.println("produto codigo  - " + produto.getCodigoProduto());
            if (item.getProduto().getCodigoProduto() == produto.getCodigoProduto()) {
                item.incrementaNumItens();

                return;
            }
        }
        //senao cria um novo item no carrinho
        itemsCarrinho.add(new Item(itemID, quantidade, produto));
    }

    /*Procura por item indicado no carrinho e define a quantidade de itens. Se
     * quantidade form 0 ou negativo, retira do carrinho. Se for positiva
     * seta a nova quantidade para o item no carrinho.
     */
    public synchronized void setQtdeItens(Livro produto, Integer quantidade) {
        Item item;
        for (int i = 0; i < itemsCarrinho.size(); i++) {
            item = (Item) itemsCarrinho.get(i);
            if (item.getProduto().getCodigoProduto() == produto.getCodigoProduto()) {
                if (quantidade <= 0) {
                    itemsCarrinho.remove(i);

                } else {
                    item.setQuantidade(quantidade);
                    //System.out.println("quantidade do form de um item - " + quantidade);
                    return;
                }
                return;
            }
        }
        //senao cria um novo item no carrinho
        itemsCarrinho.add(new Item(quantidade, produto));
    }

    public List<Item> buscarItens() {
        return (itemsCarrinho);
    }

    public double calcularTotalCarrinho() {

        double totalCarrinho = 0;
        for (Item item : itemsCarrinho) {
            double valorItem = item.calcularSubTotal();
            totalCarrinho += valorItem;
            //System.out.println("calcularTotalCarrinho - " + totalCarrinho);
        }

        return totalCarrinho;
    }

    public void remover(Integer codigoProduto) {
        //System.out.println("debug Carrinho itensdoCarrinho - " + itemsCarrinho.size());
        for (Item item : itemsCarrinho) {
            if (item.getProduto().getCodigoProduto() == codigoProduto) {
                itemsCarrinho.remove(item);
                break;
            }
        }
    }

    public Pedido confirmarPedido(Cliente cliente, Pagamento pagamento) {
        return null;
    }

    public Item buscarItem(Integer codigoItem) {
        for (Item item : itemsCarrinho) {
            if (item.getCodigoItem() == codigoItem) {
                return item;
            }
        }
        return null;
    }

    public void clear() {
        itemsCarrinho.clear();
    }
}
