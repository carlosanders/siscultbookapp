package br.com.siscultbook.bean;

import br.com.siscultbook.util.Utilitario;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * @author Grupo Projeto Final Carlos Anders, Windeson, José Elisio, Vanessa Seidel, Filipi Cavalcante
 */
public class Funcionario extends Pessoa {

    private Integer codigoFuncionario;
    private Cargo cargo;
    private GregorianCalendar dataAdmissao;
    private GregorianCalendar dataDemissao;
    private Usuario loginFuncionario;

    public Funcionario() {
        //super();
        this.loginFuncionario = new Usuario();
    }

    public Funcionario(Integer codigoFuncionario, Cargo cargo, GregorianCalendar dataAdmissao,
            GregorianCalendar dataDemissao, String nomeCompleto, GregorianCalendar dataNascimento,
            String cpf, Sexo sexo, EstadoCivil estadoCivil, Usuario loginFuncionario) {
        super(nomeCompleto, dataNascimento, cpf, sexo, estadoCivil);
        this.codigoFuncionario = codigoFuncionario;
        this.cargo = cargo;
        this.dataAdmissao = dataAdmissao;
        this.dataDemissao = dataDemissao;
        setLoginFuncionario(loginFuncionario);
        
    }

    public Integer getCodigoFuncionario() {
        return codigoFuncionario;
    }

    public void setCodigoFuncionario(Integer codigoFuncionario) {
        this.codigoFuncionario = codigoFuncionario;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public GregorianCalendar getDataAdmissao() {
        return dataAdmissao;
    }

    public Date getDataAdmissaoFormatadaBD() {
        return Utilitario.getCalendarioParaData(dataAdmissao);
    }

    public void setDataAdmissao(GregorianCalendar dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public GregorianCalendar getDataDemissao() {
        return dataDemissao;
    }

    public Date getDataDemissaoFormatadaBD() {
        return Utilitario.getCalendarioParaData(dataDemissao);
    }

    public void setDataDemissao(GregorianCalendar dataDemissao) {
        this.dataDemissao = dataDemissao;
    }

    public Usuario getLoginFuncionario() {
        return loginFuncionario;
    }

    public void setLoginFuncionario(Usuario loginFuncionario) {
        this.loginFuncionario = loginFuncionario;
    }
   
}
