package br.com.siscultbook.bean;

/**
 *
 * @author Carlos
 */
public class ProdutoCarrinho {

    private Produto produto;
    private int numProdutos;

    public ProdutoCarrinho(Produto produto) {
        setProduto(produto);
        setNumProdutos(1);
    }

    public Produto getProduto() {
        return (produto);
    }

    protected void setProduto(Produto produto) {
        this.produto = produto;
    }

    public int getNumProdutos() {
        return (numProdutos);
    }

    public void setNumProdutos(int n) {
        this.numProdutos = n;
    }

    public Integer getProdutoID() {
        return (getProduto().getCodigoProduto());
    }

    public String getDescricaoCurta() {
        return (getProduto().getDescricaoCurta());
    }

    public String getDescricaoLonga() {
        return (getProduto().getDescricaoLonga());
    }

    public double getPrecoUnitario() {
        return (getProduto().getPreco());
    }

    public void incrementeNumItens() {
        setNumProdutos(getNumProdutos() + 1);
    }

    public void cancelarPedido() {
        setNumProdutos(0);
    }

    public double getPrecoTotal() {
        return (getNumProdutos() * getPrecoUnitario());
    }
}
