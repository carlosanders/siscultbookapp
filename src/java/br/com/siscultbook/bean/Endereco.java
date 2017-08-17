package br.com.siscultbook.bean;

import java.io.Serializable;

/*
 * @author Grupo Projeto Final Carlos Anders, Windeson, José Elisio, Vanessa Seidel, Filipi Cavalcante
 */
public class Endereco implements Serializable {
    private String rua = "";
    private String bairro = "";
    private String cidade = "";
    private Estados estado;
    private String cep = "";

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    
}
