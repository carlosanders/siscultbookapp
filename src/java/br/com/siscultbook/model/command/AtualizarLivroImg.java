package br.com.siscultbook.model.command;

import br.com.siscultbook.bean.Assunto;
import br.com.siscultbook.bean.Livro;
import br.com.siscultbook.bean.Status;
import br.com.siscultbook.model.dao.InterfaceAutorDAO;
import br.com.siscultbook.model.dao.InterfaceEditoraDAO;
import br.com.siscultbook.model.dao.InterfaceLivroDAO;
import br.com.siscultbook.model.dao.InterfaceProdutoDAO;
import br.com.siscultbook.util.Utilitario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author Carlos
 */
public class AtualizarLivroImg implements InterfaceCommand {

    private InterfaceLivroDAO livroDAO;
    private InterfaceProdutoDAO produtoDAO;
    private InterfaceAutorDAO autorDAO;
    private InterfaceEditoraDAO editoraDAO;
    private String caminhoCompleto;

    public AtualizarLivroImg(InterfaceLivroDAO livroDAO, InterfaceProdutoDAO produtoDAO,
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
        request.setAttribute("titulo", "Atualizar - Livro");
        boolean retorno = false;
        String mensagem = "";
        boolean isMultiPart = FileUpload.isMultipartContent(request);
        System.out.println("isMultPart  - " + isMultiPart);
        Set<String> hashTest = new HashSet<String>();
        List<String> valores = new ArrayList<String>();

        if (isMultiPart) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setSizeMax(10485760);

            try {


                List itens = upload.parseRequest(request);
                Iterator it = itens.iterator();
                while (it.hasNext()) {
                    FileItem item = (FileItem) it.next();

                    //verfico os campos e se eles tem algum valor;
                    if (item.getFieldName().equals("arquivo")) {
                        try {
                            String path = request.getAttribute("dirAplicacao") + "/";
                            String arquivoForm = item.getString();
                            if (arquivoForm.equals("") || arquivoForm == null) {
                                mensagem = mensagem + "não foi selecionado nenhuma imagem.<br />";
                                retorno = true;
                            } else {

                                this.inserirImagemDiretorio(item, path);
                                produto.setFigura(getCaminhoCompleto());
                                System.out.println("Arquivo - " + item + "\n" + getCaminhoCompleto());
                            }

                        } catch (IOException ex) {
                            ex.printStackTrace();
                            request.setAttribute("mensagem", "Problemas com envido do arquivo: " + ex.getMessage());
                        }
                    }
                    //arquivoCadastrado
                    if (item.getFieldName().equals("arquivoCadastrado")) {
                        String arquivoCadastrado = item.getString();
                        if (arquivoCadastrado.equals("") || arquivoCadastrado == null) {
                            produto.setFigura(null);
                        } else {
                            produto.setFigura(arquivoCadastrado);

                        }
                    }

                    if (item.getFieldName().equals("isbn")) {
                        String isbn = item.getString();
                        if (isbn.equals("") || isbn == null || isbn.length() < 3) {
                            mensagem = mensagem + "O campo ISBN, deve conter no mínino 3 caracteres.<br />";
                            retorno = true;
                        } else {
                            produto.setIsbn(isbn);

                        }
                    }

                    if (item.getFieldName().equals("paginaInicial")) {
                        String paginaInicial = item.getString();
                        if (paginaInicial == null) {
                            produto.setPaginaPrincipal(null);
                        } else {
                            produto.setPaginaPrincipal("sim");
                        }
                    }

                    if (item.getFieldName().equals("titulo")) {
                        String titulo = item.getString();
                        if (titulo.equals("") || titulo == null || titulo.length() < 6) {
                            mensagem = mensagem + "O campo Título, deve conter no mínino 6 caracteres.<br />";
                            retorno = true;
                        } else {
                            produto.setTitulo(titulo);
                        }
                    }

                    if (item.getFieldName().equals("assunto")) {
                        String assunto = item.getString();
                        if (assunto.equals("") || assunto == null || assunto.length() < 5) {
                            mensagem = mensagem + "O campo Assunto, deve selecionado.<br />";
                            retorno = true;
                        } else {
                            produto.setAssunto(Assunto.valueOf(Assunto.class, assunto));
                        }
                    }

                    if (item.getFieldName().equals("estoque")) {
                        String estoque = item.getString();
                        if (estoque.equals("") || estoque == null) {
                            mensagem = mensagem + "O campo Estoque, deve ser preenchido.<br />";
                            retorno = true;
                        } else {
                            produto.setEstoque(Integer.valueOf(estoque));
                        }
                    }

                    if (item.getFieldName().equals("anoPublicacao")) {
                        String anoPublicacao = item.getString();
                        if (anoPublicacao.equals("") || anoPublicacao == null) {
                            mensagem = mensagem + "O campo Ano da Publicação, deve ser preenchido.<br />";
                            retorno = true;
                        } else {
                            produto.setAnoPublicacao(anoPublicacao);
                        }
                    }

                    if (item.getFieldName().equals("listaAutores")) {
                        String listaAutores = item.getString();
                        System.out.println("listaAutores  - " + listaAutores);
                        if (listaAutores == null) {
                            mensagem = mensagem + "O campo Autores Cadastrados, deve ser selecionado.<br />";
                            retorno = true;
                        }

                        System.out.println("Autores  - " + listaAutores);
                        valores.add(item.getString());
                        hashTest.add(listaAutores);
                    }

                    if (item.getFieldName().equals("descricaoCurta")) {
                        String descricaoCurta = item.getString();
                        if (descricaoCurta.equals("") || descricaoCurta == null) {
                            mensagem = mensagem + "O campo Descrição Curta deve ser preenchido.<br />";
                            retorno = true;
                        } else {
                            produto.setDescricaoCurta(descricaoCurta);
                        }
                    }

                    if (item.getFieldName().equals("descricaoLonga")) {
                        String descricaoLonga = item.getString();
                        if (descricaoLonga.equals("") || descricaoLonga == null) {
                            mensagem = mensagem + "O campo Descrição Longa deve ser preenchido.<br />";
                            retorno = true;
                        } else {
                            produto.setDescricaoLonga(descricaoLonga);
                        }
                    }

                    if (item.getFieldName().equals("preco")) {
                        String preco = item.getString();
                        if (preco.equals("") || preco == null) {
                            mensagem = mensagem + "O campo Preço deve ser preenchido.<br />";
                            retorno = true;
                        } else {
                            produto.setPreco(Double.parseDouble(preco));
                        }
                    }

                    if (item.getFieldName().equals("editoraCodigo")) {
                        String editoraCodigo = item.getString();
                        if (editoraCodigo.equals("") || editoraCodigo == null) {
                            mensagem = mensagem + "O campo Editoras Cadastradas, deve ser selecionado.<br />";
                            retorno = true;
                        } else {
                            produto.getEditora().setCodigoEditora(Integer.parseInt(editoraCodigo));
                        }
                    }

                    if (item.getFieldName().equals("codigo")) {
                        String codigo = item.getString();
                        if (codigo.equals("") || codigo == null) {
                            mensagem = mensagem + "O codigo do produto esta em branco.<br />";
                            retorno = true;
                        } else {
                            produto.setCodigoProduto(Integer.parseInt(codigo));
                        }
                    }

                    if (item.getFieldName().equals("statusLivro")) {
                        String statusLivro = item.getString();
                        if (statusLivro.equals("") || statusLivro == null) {
                            mensagem = mensagem + "Selecione o status.<br />";
                            retorno = true;
                        } else {
                            produto.setStatus(Status.valueOf(Status.class, statusLivro));
                        }
                    }
                }
                //System.out.println("LISTA listaAutores - " + hashTest);
                produto.setAutores(hashTest);


                if (retorno) {

                    request.setAttribute("produtoEditora", produto.getEditora().getCodigoEditora());
                    request.setAttribute("mensagem", mensagem);
                    request.setAttribute("produto", produto);
                    request.setAttribute("editoras", editoraDAO.getEditoras());
                    request.setAttribute("autores", autorDAO.getAutores());
                    request.setAttribute("livroAssunto", produto.getAssunto());
                    request.setAttribute("autoresDoLivro", hashTest);
                    request.setAttribute("erro", "Dados Incorretos:");
                    request.setAttribute("livroStatus", produto.getStatus());
                    /* caso passe por aqui retorno a página por usuario corrigir e enviar novamente */
                    return "restrito/atualizaLivro.jsp";

                } else {

                    /* caso tudo esteja correto semanticamente então vou gravar no banco */
                    produtoDAO.atualizar(produto);
                    //produto.setCodigoProduto(produtoDAO.recuperaCodigoProduto());
                    //System.out.println("codigoRecuperado - " + produto.getCodigoProduto());
                    livroDAO.excluirAutores(produto.getCodigoProduto());
                    livroDAO.atualizar(produto);
                    request.setAttribute("mensagem", "Livro (" + produto.getTitulo() + ") atualizado com sucesso!");
                    request.setAttribute("produtoEditora", produto.getEditora().getCodigoEditora());
                    //System.out.println("Valor da Figura do Servlet: " + "\n" + produto.getEditora().getCodigoEditora());
                    request.setAttribute("produto", produto);
                    request.setAttribute("editoras", editoraDAO.getEditoras());
                    request.setAttribute("autores", autorDAO.getAutores());
                    request.setAttribute("livroAssunto", produto.getAssunto());
                    request.setAttribute("autoresDoLivro", hashTest);
                    request.setAttribute("livroStatus", produto.getStatus());

                }

            } catch (FileUploadException e) {
                request.setAttribute("mensagem", "Problemas com a arquivo: " + e.getMessage());
            } catch (SQLException e) {
                request.setAttribute("mensagem", "<font color=\"#ff0000\">Problemas com a gravação: </font>" + e.getMessage());
                e.printStackTrace();
            } catch (NumberFormatException e) {
                request.setAttribute("mensagem", "Valor inválido: " + e.getMessage());
                e.printStackTrace();
            }

        } else {
            request.setAttribute("mensagem", "O formulário não possui dados binários");
            return "restrito/atualizaLivro.jsp";
        }

        return "SisCultbookController?cmd=consultarLivro";
    }

    private String inserirImagemDiretorio(FileItem item, String caminho) throws IOException {
        //Pega o diretório /imgLivros dentro do diretório atual de onde a
        //aplicação está rodando
        String path = caminho + "imgensLivros/";

        File diretorio = new File(path);

        if (!diretorio.exists()) {
            diretorio.mkdir();
        }

        // Mandar o arquivo para o diretório informado
        String nomeAnterior = item.getName();
        String extensao = nomeAnterior.substring(nomeAnterior.lastIndexOf("."), nomeAnterior.length());
        String nomeNovo = "cultbook_" + Utilitario.dataHoraHoje() + extensao;


        File file = new File(diretorio, nomeNovo);
        FileOutputStream output = new FileOutputStream(file);
        InputStream is = item.getInputStream();
        byte[] buffer = new byte[2048];
        int nLidos;

        while ((nLidos = is.read(buffer)) >= 0) {
            output.write(buffer, 0, nLidos);
        }

        //System.out.println("DATA-HORA - " + output + "\n" + diretorio + "\\" + nomeNovo);
        setCaminhoCompleto(nomeNovo);
        System.out.println("DATA-HORA - " + nomeNovo);
        output.flush();
        output.close();
        return diretorio + "\\" + nomeNovo;
    }

    public String getCaminhoCompleto() {
        return caminhoCompleto;
    }

    public void setCaminhoCompleto(String caminhoCompleto) {
        this.caminhoCompleto = caminhoCompleto;
    }
}
