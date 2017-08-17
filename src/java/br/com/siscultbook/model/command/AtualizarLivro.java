package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Assunto;
import br.com.siscultbook.bean.Autor;
import br.com.siscultbook.bean.Livro;
import br.com.siscultbook.model.dao.InterfaceAutorDAO;
import br.com.siscultbook.model.dao.InterfaceEditoraDAO;
import br.com.siscultbook.model.dao.InterfaceLivroDAO;
import br.com.siscultbook.model.dao.InterfaceProdutoDAO;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Carlos
 */
public class AtualizarLivro implements InterfaceCommand {

    private InterfaceLivroDAO livroDAO;
    private InterfaceProdutoDAO produtoDAO;
    private InterfaceAutorDAO autorDAO;
    private InterfaceEditoraDAO editoraDAO;

    public AtualizarLivro(InterfaceLivroDAO livroDAO, InterfaceProdutoDAO produtoDAO,
            InterfaceAutorDAO autorDAO, InterfaceEditoraDAO editoraDAO) {
        super();
        this.livroDAO = livroDAO;
        this.produtoDAO = produtoDAO;
        this.autorDAO = autorDAO;
        this.editoraDAO = editoraDAO;
    }

    public String execute(HttpServletRequest request, HttpServletResponse response) {

        Livro produto = new Livro();

        /* adciono um título para a página */
        request.setAttribute("titulo", "Cadastro - Livro");

        boolean retorno = false;
        String mensagem = "";

        try {

            String isbn = request.getParameter("isbn");
            if (isbn.equals("") || isbn == null || isbn.length() < 3) {
                mensagem = mensagem + "O campo ISBN, deve conter no mínino 3 caracteres.<br />";
                retorno = true;
            } else {
                produto.setIsbn(isbn);
            }

            String titulo = request.getParameter("titulo");
            if (titulo.equals("") || titulo == null || titulo.length() < 6) {
                mensagem = mensagem + "O campo Título, deve conter no mínino 6 caracteres.<br />";
                retorno = true;
            } else {
                produto.setTitulo(titulo);
            }

            String assunto = request.getParameter("assunto");
            if (assunto.equals("") || assunto == null || assunto.length() < 5) {
                mensagem = mensagem + "O campo Assunto, deve selecionado.<br />";
                retorno = true;
            } else {
                produto.setAssunto(Assunto.valueOf(Assunto.class, assunto));
            }

            String estoque = request.getParameter("estoque");
            if (estoque.equals("") || estoque == null) {
                mensagem = mensagem + "O campo Estoque, deve ser preenchido.<br />";
                retorno = true;
            } else {
                produto.setEstoque(Integer.valueOf(estoque));
            }

            String figura = request.getParameter("figura");
            if (figura.equals("") || figura == null) {
                mensagem = mensagem + "O campo Figura, deve ser preenchido.<br />";
                retorno = true;
            } else {
                produto.setFigura(figura);
            }

            String anoPublicacao = request.getParameter("anoPublicacao");
            if (anoPublicacao.equals("") || anoPublicacao == null) {
                mensagem = mensagem + "O campo Ano da Publicação, deve ser preenchido.<br />";
                retorno = true;
            } else {
                produto.setAnoPublicacao(anoPublicacao);
            }

            String listaAutores = request.getParameter("listaAutores");
            if (request.getParameter("listaAutores") == null || listaAutores == null) {
                mensagem = mensagem + "O campo Autores Cadastrados, deve ser selecionado.<br />";
                retorno = true;
            }

            String descricaoCurta = request.getParameter("descricaoCurta");
            if (descricaoCurta.equals("") || descricaoCurta == null) {
                mensagem = mensagem + "O campo Descrição Curta deve ser preenchido.<br />";
                retorno = true;
            } else {
                produto.setDescricaoCurta(descricaoCurta);
            }

            String descricaoLonga = request.getParameter("descricaoLonga");
            if (descricaoLonga.equals("") || descricaoLonga == null) {
                mensagem = mensagem + "O campo Descrição Longa deve ser preenchido.<br />";
                retorno = true;
            } else {
                produto.setDescricaoLonga(descricaoLonga);
            }

            String preco = request.getParameter("preco");
            if (preco.equals("") || preco == null) {
                mensagem = mensagem + "O campo Preço deve ser preenchido.<br />";
                retorno = true;
            } else {
                produto.setPreco(Double.parseDouble(preco));
            }

            String editoraCodigo = request.getParameter("editoraCodigo");
            if (editoraCodigo.equals("") || editoraCodigo == null) {
                mensagem = mensagem + "O campo Editoras Cadastradas, deve ser selecionado.<br />";
                retorno = true;
            } else {
                produto.getEditora().setCodigoEditora(Integer.parseInt(editoraCodigo));
            }

            /* Após passar por cada if acimo vamos fazer uma verificação se todos os dados vindo do form estão de
             * acordo. Se a variavel retorno for true então algo está errado e precisa ser corrigido
             * senão tudo está ok então daremos prosseguimento a gravação no bando de dados
             */
            //seto os valores do form no bean Livro pelo objeto produto
            //produto.setIsbn(isbn);
            //produto.setTitulo(titulo);
            //produto.setAssunto(Assunto.valueOf(Assunto.class, assunto));
            //cliente.getEndereco().setEstado(Estados.valueOf(Estados.class, valorEstado));
            //produto.setEstoque(Integer.valueOf(estoque));
            //produto.setFigura(figura);
            //produto.setAnoPublicacao(anoPublicacao);


            String[] teste;
            teste = request.getParameterValues("listaAutores");

            Set<String> hashTest = new HashSet<String>();
            if (teste != null) {

            for (int i = 0; i < teste.length; i++) {
            //pegar apenas valores selecionados
            System.out.println("debug listaAutores - " + teste[i]);
            hashTest.add(teste[i]);
            }
            produto.setAutores(hashTest);

            }

            //produto.setDescricaoCurta(descricaoCurta);
            //produto.setDescricaoLonga(descricaoLonga);
            //produto.setPreco(Double.parseDouble(preco));
            //produto.getEditora().setCodigoEditora(Integer.valueOf(editoraCodigo));
            produto.setCodigoProduto(Integer.parseInt(request.getParameter("codigo")));


            if (retorno) {

                request.setAttribute("produtoEditora", produto.getEditora().getCodigoEditora());
                request.setAttribute("mensagem", mensagem);
                request.setAttribute("produto", produto);
                request.setAttribute("editoras", editoraDAO.getEditoras());
                request.setAttribute("autores", autorDAO.getAutores());
                request.setAttribute("livroAssunto", produto.getAssunto());
                request.setAttribute("teste", teste);

                /* caso passe por aqui retorno a página por usuario corrigir e enviar novamente */
                return "restrito/atualizaLivro.jsp";

            } else {
                /* caso tudo esteja correto semanticamente então vou gravar no banco */
                produtoDAO.atualizar(produto);
                //produto.setCodigoProduto(produtoDAO.recuperaCodigoProduto());
                livroDAO.atualizar(produto);
                request.setAttribute("mensagem", "Livro (" + produto.getTitulo() + ") atualiazdo com sucesso!");
                request.setAttribute("codigoEditoraCadastrada", produto.getEditora().getCodigoEditora());
                request.setAttribute("produto", produto);
                request.setAttribute("editoras", editoraDAO.getEditoras());
                request.setAttribute("autores", autorDAO.getAutores());
                request.setAttribute("livroAssunto", produto.getAssunto());

                List<Autor> autores = livroDAO.lerAutores(Integer.parseInt(request.getParameter("codigo")));

                Set<Integer> hashAutor = new HashSet<Integer>();
                for (int i = 0; i < autores.size(); i++) {
                    //adiciono no Set somente o codigo do Autor e vou
                    //enviar pelo request para autoresDoLivro
                    hashAutor.add(autores.get(i).getCodigoAutor());
                }
                //dados relativos ao livro dadastrado
                request.setAttribute("autoresDoLivro", hashAutor);
                //request.setAttribute("autoresDoLivro", teste);


            }


        } catch (SQLException e) {
            request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com a atualização: </font>" + e.getMessage());
            e.printStackTrace();
        } catch (NumberFormatException e) {
            request.setAttribute("mensagem", "Valor inválido: " + e.getMessage());
            e.printStackTrace();
        }


        return "SisCultbookController?cmd=consultarLivro";
    }
}
