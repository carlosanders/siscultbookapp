package br.com.siscultbook.bean;

/**
 *
 * @author Carlos
 */
public class Editora {

    private Integer codigoEditora;
    private String nomeEditora;
    private String cnpj;
    private String obs;
    private Status status;
    

    public Editora() {
    }

    public Editora(Integer codigoEditora, String nomeEditora,
            String cnpj, String obs) {
        setCodigoEditora(codigoEditora);
        setNomeEditora(nomeEditora);
        setCnpj(cnpj);
        setObs(obs);
    }

    public Integer getCodigoEditora() {
        return codigoEditora;
    }

    public void setCodigoEditora(Integer codigoEditora) {
        this.codigoEditora = codigoEditora;
    }

    public String getNomeEditora() {
        return nomeEditora;
    }

    public void setNomeEditora(String nomeEditora) {
        this.nomeEditora = nomeEditora;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
