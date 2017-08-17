package br.com.siscultbook.bean;

import br.com.siscultbook.util.Utilitario;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

/*
 * @author Grupo Projeto Final Carlos Anders, Windeson, José Elisio, Vanessa Seidel, Filipi Cavalcante
 */
public abstract class Pessoa {

    private String nomeCompleto = "";
    private GregorianCalendar dataNascimento;
    private String cpf = "";
    private Sexo sexo;
    private Endereco endereco;
    private EstadoCivil estadoCivil;

    public Pessoa() {
        this.endereco = new Endereco();
    }

    public Pessoa(String nomeCompleto, GregorianCalendar dataNascimento,
            String cpf, Sexo sexo, EstadoCivil estadoCivil) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
    }

    public Pessoa(String nomeCompleto, GregorianCalendar dataNascimento,
            String cpf, Sexo sexo, Object[] endereco,
            EstadoCivil estadoCivil) {
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.sexo = sexo;
        //dados do endereco
        this.endereco = new Endereco();
        this.endereco.setRua((String) endereco[0]);
        this.endereco.setBairro((String) endereco[1]);
        this.endereco.setCidade((String) endereco[2]);
        this.endereco.setEstado((Estados) endereco[3]);

        this.estadoCivil = estadoCivil;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public GregorianCalendar getDataNascimento() {
        return dataNascimento;
    }

    public Date getDataNascimentoFormatadaBD() {
        return Utilitario.getCalendarioParaData(dataNascimento);
    }

    public void setDataNascimento(GregorianCalendar dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
}
