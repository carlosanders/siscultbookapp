/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscultbook.bean;

/**
 *
 * @author Carlos
 */
public class PagamentoCartao extends Pagamento{

    private String numeroCartao;
    private String validadeCartao;
    private TipoCartao bandeira;
    private String codigoSeguranca;
    private String nomeTitular;
    private Integer numeroParcelas;
    private double valorPacela;

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public void setNumeroCartao(String numeroCartao) {
        this.numeroCartao = numeroCartao;
    }

    public String getValidadeCartao() {
        return validadeCartao;
    }

    public void setValidadeCartao(String validadeCartao) {
        this.validadeCartao = validadeCartao;
    }

    public TipoCartao getBandeira() {
        return bandeira;
    }

    public void setBandeira(TipoCartao bandeira) {
        this.bandeira = bandeira;
    }

    public String getCodigoSeguranca() {
        return codigoSeguranca;
    }

    public void setCodigoSeguranca(String codigoSeguranca) {
        this.codigoSeguranca = codigoSeguranca;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public Integer getNumeroParcelas() {
        return numeroParcelas;
    }

    public void setNumeroParcelas(Integer numeroParcelas) {
        this.numeroParcelas = numeroParcelas;
    }

    public double getValorPacela() {
        return valorPacela;
    }

    public void setValorPacela(double valorPacela) {
        this.valorPacela = valorPacela;
    }

    

}
