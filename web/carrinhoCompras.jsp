<%@page import="br.com.siscultbook.bean.Item"%>
<%@page import="br.com.siscultbook.bean.ProdutoCarrinho"%>
<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="br.com.siscultbook.bean.Livro"%>
<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="java.util.List, br.com.siscultbook.bean.Cliente" %>

<link href="cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="css/catalogoLivro.css" />
<%----%>        
<div id="catalogoLivro">

    <div class="faixa_amarela" id="faixa_amarela-lins_cadastro" >
        &nbsp;${titulo}
    </div>

    <h3>${mensagem}</h3>
    <%--
        Página é visão que lista todos os clientes cadastrados no banco de dados
    --%>
    <table width="710" border="0" align="center" cellpadding="0" cellspacing="0" id="table_eventos_cadastrados">
        <thead>
            <tr>
                <th width="57" height="23" align="center">Código</th>
                <th width="432" align="left">&nbsp;Descrição</th>
                <th width="59" align="center">Excluir</th>
                <th width="75" align="center">              R$</th>
                <th width="106" align="center">Quantidade</th>
                <th width="159" align="center">Preço Total</th>
            </tr>
        </thead>
        <%
                    String classe = "linha1";
                    if (request.getAttribute("itensCarrinho") != null ) {
                        List itemsOrdered = (List) request.getAttribute("itensCarrinho");
                        Item order;
                        for (int i = 0; i < itemsOrdered.size(); i++) {
                            order = (Item) itemsOrdered.get(i);


        %>
        <form action="SisCultbookController" method="GET">
            <input type='hidden' name='cmd' value="atualizarCarrinho" />
            <input type='hidden' name='itemID' value="<%=order.getProduto().getCodigoProduto()%>" />

            <tr class="<%=classe%>">
                <td align="center"><%=order.getProduto().getCodigoProduto()%></td>
                <td align="left"><%=order.getProduto().getDescricaoCurta()%></td>
                <td align="center">
                    <a href="SisCultbookController?cmd=removerItemCarrinho&itemID=<%=order.getProduto().getCodigoProduto()%>" title="remover item do carrinho de compras">
                        <img src="images/Shopping_Cart_Remove.png" alt="Apagar Livro" width="42" height="42" border="0" />
                    </a>
              </td>
                <td align="center"><%=order.getProduto().getPreco()%></td>
                <td align="center" valign="middle">
                  <input type='text' name='quantidade' style="width:15px; height:18px;" value="<%=order.getQuantidade()%>" />
                  <input type="submit" name="submit" value="" class="submit" title="Atualizar carrinho de compras" />
                </td>
              <td align="center"><%=order.calcularSubTotal()%></td>
            </tr>
        </form>

        <%
                            classe = classe.equals("linha1") ? "linha2" : "linha1";
                        }
        %>
        <tr class="<%=classe%>">
          <td colspan="6" align="right">&nbsp;</td>
        </tr>
        <tr class="linhaTotal">
            <td height="25" colspan="5" align="right"><strong>Valor total do(s) produto(s):&nbsp;</strong></td>
            <td align="center">${valorTotalCarrinho}</td>
        </tr>
        <tr class="">
           <form action="SisCultbookController" method="POST">
            <input type='hidden' name='cmd' value="atualizarFrete" />
            <input type='hidden' name='item' value="" />
             <td height="40" colspan="6" align="right">
             	<table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="170" align="center">Calcular Frete:</td>
                    <td width="170" align="right">CEP:</td>
                    <td width="170" align="center"><input type='text' name='cep' style="width:130px; height:18px;" value="" /></td>
                    <td width="170" align="center"><input type="submit" name="submit" value="calcular" title="calcular" /></td>
                    <td width="170" align="center">valor R$: </td>
                  </tr>
                </table>
             </td>
             </form>
        </tr>
        <tr class="linhaContinuarComprando">
             <td height="40" colspan="6" align="right">
             	<table width="850" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td width="170" align="center">Itens no carrinho:</td>
                    <td width="170" align="center">Esvaziar carrinho</td>
                    <td width="170" align="center">
                        <a href="SisCultbookController?cmd=consultarCatalogo" title="continuar comprando">continuar comprando</a>
                    </td>
                    <td width="170" align="center">
                        <a href="SisCultbookController?cmd=finalizarCompra" title="fechar compra">fechar compra</a>
                    </td>
                  </tr>
                </table>
             </td>
        </tr>
        <%
                     }else{
        %>
        <tr class="<%=classe%>">
            <td colspan="6" align="center">&nbsp;</td>
        </tr>
        <tr class="<%=classe%>">
            <td height="33" colspan="6" align="center"><strong>Não consta nenhum registro!</strong></td>
        </tr>
        <%
                    }
        %>
        
    </table>

</div>