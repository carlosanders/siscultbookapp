<%@page import="br.com.siscultbook.bean.Livro"%>
<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="java.util.List, br.com.siscultbook.bean.Cliente" %>

<link href="cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />

<link rel="stylesheet" type="text/css" href="css/catalogoLivro.css" />
<%----%>        
<div id="catalogoLivro">
            <!-- path -->

        <div id="path">
            <ul>
                <li class="base">Você está aqui:</li>
                <li class="first">${titulo}</li>

            </ul>
        </div>
        <!-- end path -->
    <div id="erro">
        	${mensagem}
        </div>
    <%--
        Página é visão que lista todos os clientes cadastrados no banco de dados
    --%>
    <table width="650" border="0" align="center" cellpadding="0" cellspacing="0" id="table_eventos_cadastrados">
        <thead>
            <tr>
                <th width="150" align="left">Desc. Curta</th>
                <th width="306" align="left">Desc. Longa</th>
                <th width="100" align="left">Preço</th>
                <th width="100" >Carrinho</th>
            </tr>
        </thead>
        <%
                    //String formURL = response.encodeURL("/WebExemplos/pedido.jsp");
                    String classe = "linha1";
                    List<Livro> livros = (List<Livro>) request.getAttribute("livros");
                    for (Livro livro : livros) {


        %>
        <form action="SisCultbookController" method="GET">
            <input type='hidden' name='cmd' value="adicionarCarrinho" />
            <input type='hidden' name='itemID' value="<%=livro.getCodigoProduto()%>" />
            <tr class="<%=classe%>">
                <td align="left"><%=livro.getDescricaoCurta()%></td>
                <td align="left"><%=livro.getDescricaoLonga()%></td>
                <td align="left"><%=livro.getPreco()%></td>
                
                <td width="202" align="center"><input type="submit" value="" class="carrinhoCompras" title="adicionar no carrinho" /></td>
            </tr>
        </form>

        <%
                        classe = classe.equals("linha1") ? "linha2" : "linha1";
                    }
        %>
    </table>



</div>