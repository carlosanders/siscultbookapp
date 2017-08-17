/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.bean;

import java.util.Collections;
import java.util.Set;

/**
 *
 * @author home
 */
public abstract class Publicacao extends Produto {

    private String titulo;
    private Assunto assunto;
    private Integer estoque;
    private String figura;
    private String anoPublicacao;
    private Set autores;
    private Editora editora;
    private String paginaPrincipal;
    private Status status;

    public Publicacao() {
        this.editora = new Editora();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Assunto getAssunto() {
        return assunto;
    }

    public void setAssunto(Assunto assunto) {
        this.assunto = assunto;
    }

    public Integer getEstoque() {
        return estoque;
    }

    public void setEstoque(Integer estoque) {
        this.estoque = estoque;
    }

    public String getFigura() {
        return figura;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public Editora getEditora() {
        return editora;
    }

    public void setEditora(Editora editora) {
        this.editora = editora;
    }

    public Set getAutores() {
        return Collections.unmodifiableSet(this.autores);
    }

    public void setAutores(Set autores) {
        this.autores = autores;
    }

    public String getPaginaPrincipal() {
        return paginaPrincipal;
    }

    public void setPaginaPrincipal(String paginaPrincipal) {
        this.paginaPrincipal = paginaPrincipal;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
