package br.com.siscultbook.bean;

/**
 *
 * @author Carlos
 */
public class Acesso {

    private Integer codigoAcesso;
    private NivelDeAcesso nivelDeAcesso;
    private String comando;
    private String descricao;

    public Acesso(){
        this.nivelDeAcesso = new NivelDeAcesso();
    }

    public Integer getCodigoAcesso() {
        return codigoAcesso;
    }

    public void setCodigoAcesso(Integer codigoAcesso) {
        this.codigoAcesso = codigoAcesso;
    }

    public String getComando() {
        return comando;
    }

    public void setComando(String comando) {
        this.comando = comando;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public NivelDeAcesso getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    public void setNivelDeAcesso(NivelDeAcesso nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }
}
