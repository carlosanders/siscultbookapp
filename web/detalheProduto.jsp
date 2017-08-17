<%@page import="br.com.siscultbook.bean.Autor"%>
<%@page import="br.com.siscultbook.bean.Livro"%>
<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="java.util.List, br.com.siscultbook.bean.Cliente" %>
<link href="cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />

<link rel="stylesheet" type="text/css" href="css/consultaCliente.css" />
<%----%>        
<div id="consultaCliente">

    <!-- path -->

    <div id="path">
        <ul>
            <li class="base">Você está aqui:</li>
            <li class="first">${titulo}</li>

        </ul>
    </div>
    <!-- end path -->
    <div id="erro">
        <font color="#FF0000" style="font-weight:bold">${erro}</font>        
        ${mensagem}
    </div>
    <%--
        Página é visão que lista todos os clientes cadastrados no banco de dados
    --%>

    <%
                Livro livro;
                if (request.getAttribute("livro") == null) {
                    out.println("Erro: Livro não encontrado");
                } else {

                    livro = (Livro) request.getAttribute("livro");
    %>

    <div class="productIMG">
        <img  src="imgensLivros/<%=livro.getFigura()%>" alt="" border="0">
  </div>
    <h1 class="product"><%=livro.getTitulo()%></h1>
    <%
                        if (request.getAttribute("autoresDoLivro") == null) {
                            out.println("Erro: Autore(s) não encontrado(s).");
                        } else {
                            List<Autor> autores = (List<Autor>) request.getAttribute("autoresDoLivro");
                            for (Autor autor : autores) {

    %>
    <a href="SisCultbookController?cmd=consultarLivrosPorAutor&amp;codigo=<%=autor.getCodigoAutor()%>" class="autorSale"><span>+</span><%=autor.getSobrenome()%></a>,

    <%                      }
                        }
    %>
    <br>
    <br>
    <form id="car" action="SisCultbookController" method="GET">
        <input type='hidden' name='cmd' value="adicionarCarrinho" />
        <input type='hidden' name='itemID' value="<%=livro.getCodigoProduto()%>" />
        <!-- preço -->
        <div class="priceHolder">
            <div class="price">
                <div class="push13"></div>
                <span class="for">por <%=Utilitario.formatarParaMonetario(livro.getPreco()) %></span>
            </div>
            <input type="submit" value="" class="carrinhoCompras" title="adicionar no carrinho" />
        </div>
    </form>
    <br>
    <br>
    <br>
    <span class="productDesc">Descrição</span>
    <p class="product"><%=livro.getDescricaoLonga()%></p>
    <br clear="left">

    <span class="productDesc">Detalhes do Produto</span>
    <p class="product">
        <strong>Ano de Publicação:</strong> <%=livro.getAnoPublicacao()%><br>
        <strong>Editora:</strong> <%=livro.getEditora().getNomeEditora()%><br>
        <strong>ISBN:</strong> <%=livro.getIsbn()%><br>
        <strong>Estoque:</strong> <%=livro.getEstoque()%><br>
    </p>
    <br clear="left">
    <br clear="left">
    <%      }%>
</div>