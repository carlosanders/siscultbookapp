/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.siscultbook.bean;

import java.util.GregorianCalendar;

/**
 *
 * @author Carlos
 */
public class PagamentoBoleto extends Pagamento{

    private String numeroDocumento;
    private GregorianCalendar vencimento;
    private String cedente;

    public PagamentoBoleto(){
        
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public GregorianCalendar getVencimento() {
        return vencimento;
    }

    public void setVencimento(GregorianCalendar vencimento) {
        this.vencimento = vencimento;
    }

    public String getCedente() {
        return cedente;
    }

    public void setCedente(String cedente) {
        this.cedente = cedente;
    }

}
