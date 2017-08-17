<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="br.com.siscultbook.bean.Livro"%>
<%@page import="br.com.siscultbook.bean.Item"%>
<%@page import="java.util.List"%>
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
    <img src="images/carrinho2.png">
    <div class="push20"></div>


    <table class="shopCart" border="0" cellpadding="0" cellspacing="0" width="745">
        <tbody>
            <tr>
                <th>&nbsp;</th>
                <th>Produto</th>
                <th>Retirar Item</th>
                <th>Quantidade</th>
                <th>Valor Unitário</th>
                <th>Valor Total</th>
            </tr>
            <%
                        String classe = "linha1";
                        if (request.getAttribute("itensCarrinho") != null) {
                            List itemsOrdered = (List) request.getAttribute("itensCarrinho");
                            Item order;
                            //Produto livro;
                            for (int i = 0; i < itemsOrdered.size(); i++) {
                                order = (Item) itemsOrdered.get(i);
                                //livro = (Livro) itemsOrdered.get(i);

            %>

            <tr>
                <td width="90">
                    <center>
                        <a href="SisCultbookController?cmd=detalhesProduto&cod=<%=order.getProduto().getCodigoProduto()%>">
                            <img alt=""  src="imgensLivros/<%=order.getProduto().getFigura()%>"></a>
                    </center>
                </td>
                <td width="300">
                    <a href="SisCultbookController?cmd=detalhesProduto&cod=<%=order.getProduto().getCodigoProduto()%>" class="autorSale">
                        <%=order.getProduto().getTitulo()%>
                    </a>
                </td>
                <td width="40">
                    <center>
                        <a href="SisCultbookController?cmd=removerItemCarrinho&itemID=<%=order.getProduto().getCodigoProduto()%>" title="remover item do carrinho de compras">
                            <img alt=""  src="images/remove.gif" title="Remover este item" border="0">
                        </a>
                    </center>
                </td>

                <td width="70">
                    <form id="car" action="SisCultbookController" method="GET">
                        <input type='hidden' name='cmd' value="atualizarCarrinho" />
                        <input type='hidden' name='itemID' value="<%=order.getProduto().getCodigoProduto()%>" />
                        <input type='text' name='quantidade' style="width:15px; height:18px;" value="<%=order.getQuantidade()%>" />&nbsp;
                        <input type="submit" name="submit" value="" class="submit" title="Atualizar carrinho de compras" />
                    </form>
                </td>

                <td width="70"><%=Utilitario.formatarParaMonetario(order.getProduto().getPreco())%></td>
                <td width="85"><%=Utilitario.formatarParaMonetario(order.calcularSubTotal())%></td>
            </tr>
            <%
                                            classe = classe.equals("linha1") ? "linha2" : "linha1";
                                        }
            %>



            <tr>
                <td style="border-bottom: medium none rgb(255, 255, 255);"></td>
                <td style="border-bottom: medium none rgb(255, 255, 255);"></td>
                <th><b>Subtotal</b></th>
                <th colspan="2">
                    <center>
                        Quantidade Total: 
                        <b>
                            <% if (itemsOrdered.size() <= 1) {%>
                            <%=itemsOrdered.size()%> item
                            <%
                             } else {
                            %>
                            <%=itemsOrdered.size()%> itens
                            <%
                                                        }
                            %>
                        </b>
                    </center>
                </th>

                <th>${valorTotalCarrinho}</th>
            </tr>

            <tr>
                <td style="border-bottom: medium none rgb(255, 255, 255);"></td>
                <td style="border-bottom: medium none rgb(255, 255, 255);"></td>
                <th colspan="3"><b>Frete </b></th>
                <th><%=Utilitario.formatarParaMonetario((Double) session.getAttribute("valorCep"))%></th>
            </tr>

            <tr>
                <td style="border-bottom: medium none rgb(255, 255, 255);"></td>
                <td style="border-bottom: medium none rgb(255, 255, 255);"></td>
                <th colspan="3"><b>Total</b></th>
                <th><b>${valorTotalPedido}</b></th>
            </tr>
            <%
                                    } else {
            %>
            <tr>
                <td colspan="6" style="border-bottom: medium none rgb(255, 255, 255);"><strong>Não consta nenhum registro!</strong></td>
            </tr>
            <%                        }
            %>
        </tbody>

    </table>


    <div class="push20"></div>
    <div class="push20"></div>

    <span class="shopDesc">Você pode optar pelo frete Normal ou pelo envio por Sedex.</span>
    <div class="push10"></div>
    <span class="shopDesc">No final do processo de compra você pode confirmar a forma de envio.</span>

    <div class="push10"></div>
    <p class="cart">
        <strong>Frete Normal</strong> - Gratuito para todo Brasil (Prazo de entrega 10 a 15 dias úteis)
        <br clear="left">
    </p>

    <form name="cep" action="SisCultbookController" method="post" onsubmit="return valida(this)">
        <input type='hidden' name='cmd' value="calcularCep" />
        <p class="cart">
            <strong>Simulação de frete Sedex</strong> - (Prazo de entrega 3 a 5 dias úteis)
            <br clear="left">
	    Digite o CEP de entrega dos produtos para uma simulação do valor do Sedex.<br>
            <input id="cep" maxlength="5" class="campo" size="4" name="cep" value="">-<input maxlength="3" class="campo" size="1" name="cepc" value=""> &nbsp;&nbsp; <input class="botao" value="Calcular Sedex" name="enviar" type="submit"> <br><br>
        </p>
    </form>

    <br clear="left">
    <br clear="left">

    <div style="margin-left: 150px;">
        <div style="float: left;">
            <a href="SisCultbookController?cmd=paginaPrincipal"><img src="images/bt_buy2.png" border="0"></a>
        </div>
        <div style="float: right;">
            <a href="SisCultbookController?cmd=finalizarCompra" id="btn-continuar" ><img alt=""  src="images/bt_next.png" border="0"></a>
        </div>
    </div>
    <script type="text/javascript">
        $(document).ready(function(){
            $('#btn-continuar').m2brDialog({
                tipo			: 'erro',
                titulo			: 'Erro!',
                texto			: 'Por favor, preencha o campo CEP, para c&aacute;lculo do frete!',
                condicao		: {
                    origem		:	function() { return ($('#cep').val() ? true : false); },
                    retorno		:	function() { return true; }
                }
            });
        });//fim da funcao
    </script>

    <br clear="left">
    <br clear="left">
    <br clear="left">

</div>