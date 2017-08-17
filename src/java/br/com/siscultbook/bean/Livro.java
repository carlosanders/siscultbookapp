package br.com.siscultbook.bean;

/**
 *
 * @author carlosanders
 */
public class Livro extends Publicacao {

    private String isbn;
   

    //construtor
    public Livro() {
                
    }

    //GET/SET
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    
}
