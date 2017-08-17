package br.com.siscultbook.bean;

/**
 *
 * @author Carlos
 */
public class Item {

    private Integer codigoItem;
    private Integer quantidade;
    private Produto produto;

    public Item() {
        this.produto = new Livro();
    }

    
    public Item(Integer quantidade, Produto produto) {
        this.quantidade = quantidade;
        this.produto = produto;
    }

    public Item(Integer codigoItem, Integer quantidade, Produto produto) {
        this(quantidade, produto);
        this.codigoItem = codigoItem;
    }

    public double calcularSubTotal() {
        //System.out.println("calcularSubtoral - " + produto.getPreco() * quantidade);
        return (produto.getPreco() * quantidade);

    }

    public void incrementaNumItens() {
        setQuantidade(getQuantidade() + 1);
        //System.out.println("quantidade setado  - " + getQuantidade());
    }

    //get e set
    public Integer getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(Integer codigoItem) {
        this.codigoItem = codigoItem;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
    //feito o cast pq o livro é um produto
    //feito para nao mudar a arquitetura
    public Livro getProduto() {
        return (Livro) produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }
}
