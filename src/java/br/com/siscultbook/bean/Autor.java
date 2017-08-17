package br.com.siscultbook.bean;

import java.util.GregorianCalendar;

/**
 *
 * @author Carlos
 */
public class Autor extends Pessoa {

    private Integer codigoAutor;
    private String sobrenome;
    private Status status;

    public Autor() {
    }

    public Autor(String nomeCompleto, GregorianCalendar dataNascimento,
            String cpf, Sexo sexo, EstadoCivil estadoCivil, Integer codigoAutor,
            String sobrenome) {
        super(nomeCompleto, dataNascimento, cpf, sexo, estadoCivil);
        setCodigoAutor(codigoAutor);
        setSobrenome(sobrenome);

    }

    public Integer getCodigoAutor() {
        return codigoAutor;
    }

    public void setCodigoAutor(Integer codigoAutor) {
        this.codigoAutor = codigoAutor;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
