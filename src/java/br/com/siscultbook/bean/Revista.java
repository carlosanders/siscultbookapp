/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.siscultbook.bean;

/**
 *
 * @author home
 */
public class Revista extends Publicacao {

    private String issn;

    //construtor
    public Revista() {
    }        

    //GET/SET
    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn;
    }
    
    
}
