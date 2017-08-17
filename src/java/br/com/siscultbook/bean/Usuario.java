package br.com.siscultbook.bean;

import br.com.siscultbook.util.Utilitario;
import java.sql.Timestamp;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Carlos
 */
public class Usuario {

    private String senha;
    private NivelDeAcesso nivelDeAcesso;
    private GregorianCalendar ultimoAcesso;
    private Status status;

    public Usuario() {
        this.nivelDeAcesso = new NivelDeAcesso();
    }

    public Usuario(String senha, NivelDeAcesso nivelDeAcesso,
            GregorianCalendar ultimoAcesso, Status status) {
        setSenha(senha);
        setNivelDeAcesso(nivelDeAcesso);
        setUltimoAcesso(ultimoAcesso);
        setStatus(status);

    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public GregorianCalendar getUltimoAcesso() {
        return ultimoAcesso;
    }

    public Date getUltimoAcessoFormatadaParaBD() {
        return Utilitario.getCalendarioParaData(ultimoAcesso);
    }

    public Timestamp getUltimoAcessoFormatadaParaTimeStamp() {
        return Utilitario.getCalendarioData(ultimoAcesso);
    }

    public void setUltimoAcesso(GregorianCalendar ultimoAcesso) {
        this.ultimoAcesso = ultimoAcesso;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public NivelDeAcesso getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    public void setNivelDeAcesso(NivelDeAcesso nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }
}
