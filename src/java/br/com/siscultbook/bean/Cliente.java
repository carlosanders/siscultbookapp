package br.com.siscultbook.bean;

import br.com.siscultbook.util.Utilitario;
import java.util.Date;
import java.util.GregorianCalendar;

/*
 * @author Grupo Projeto Final Carlos Anders, Windeson, José Elisio, Vanessa Seidel, Filipi Cavalcante
 */
public class Cliente extends Pessoa {

    private Integer codigoCliente;
    private String email;
    private String  telefone;
    private GregorianCalendar desde;
    private Usuario loginCliente;
    private boolean cont;

    public Cliente() {
        this.loginCliente = new Usuario();
    }

    public Cliente(String nomeCompleto, GregorianCalendar dataNascimento,
            String cpf, Sexo sexo, EstadoCivil estadoCivil) {
        super(nomeCompleto, dataNascimento, cpf, sexo, estadoCivil);
    }

    public Cliente(String nomeCompleto, GregorianCalendar dataNascimento,
            String cpf, String login, String senha, Sexo sexo, EstadoCivil estadoCivil,
            Integer codigoCliente, String email, String telefone, GregorianCalendar desde,
            Usuario loginCliente) {
        this(nomeCompleto, dataNascimento, cpf, sexo, estadoCivil);

        this.codigoCliente = codigoCliente;
        this.email = email;
        this.telefone = telefone;
        this.desde = desde;
        setLoginCliente(loginCliente);
    }

    public Integer getCodigoCliente() {
        boolean cont;
        return codigoCliente;
    }

    public void setCodigoCliente(Integer codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public GregorianCalendar getDesde() {
        return desde;
    }

    public Date getDesdeFormatadaParaBD() {
        return Utilitario.getCalendarioParaData(desde);
    }

    public void setDesde(GregorianCalendar desde) {
        this.desde = desde;
    }

    public Usuario getLoginCliente() {
        return loginCliente;
    }

    public void setLoginCliente(Usuario loginCliente) {
        this.loginCliente = loginCliente;
    }
}
