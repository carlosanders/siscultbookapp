
<%@page import="br.com.siscultbook.bean.Cliente"%>
<%@page import="br.com.siscultbook.bean.PagamentoCartao"%>
<%@page import="br.com.siscultbook.bean.PagamentoBoleto"%>
<%@page import="br.com.siscultbook.bean.Entrega"%>
<%@page import="br.com.siscultbook.bean.TipoCartao"%>
<%@page import="br.com.siscultbook.bean.Estados"%>
<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="br.com.siscultbook.bean.Item"%>
<%@page import="java.util.List"%>
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
        <font color="#FF0000" style="font-weight:bold">${erro}</font>
        <br />
        <font color="#FF0000">${mensagem}</font>
    </div>
    <div id="sobre" >

        <img src="images/carrinho2.png">
        <div class="push20"></div>
        <div class="push20"></div>

        <!-- Flag: False-->
        <table class="shopCart" border="0" cellpadding="0" cellspacing="0" width="745">
            <tbody><tr>
                    <th>&nbsp;</th>
                    <th>Produto</th>
                    <th>Quantidade</th>
                    <th>Valor Unitário</th>
                    <th>Valor Total</th>
                </tr>
                <%
                            String classe = "linha1";

                            if (request.getAttribute("itens") != null) {
                                List itemsOrdered = (List) request.getAttribute("itens");
                                Item order;
                                //Produto livro;
                                for (int i = 0; i < itemsOrdered.size(); i++) {
                                    order = (Item) itemsOrdered.get(i);
                                    //livro = (Livro) itemsOrdered.get(i);

                %>

                <tr>
                    <td width="90"><center>
                            <a href="SisCultbookController?cmd=detalhesProduto&cod=<%=order.getProduto().getCodigoProduto()%>">
                                <img  src="imgensLivros/<%=order.getProduto().getFigura()%>" alt="" border="0">
                            </a>
                        </center>
                    </td>
                    <td width="300">
                        <a href="SisCultbookController?cmd=detalhesProduto&cod=<%=order.getProduto().getCodigoProduto()%>" class="autorSale">
                            <%=order.getProduto().getTitulo()%>
                        </a>
                    </td>

                    <td width="70">
                        <span class="autorSale"><%=order.getQuantidade()%></span>
                    </td>

                    <td width="70"><%=Utilitario.formatarParaMonetario(order.getProduto().getPreco())%></td>

                    <td width="85"><%=Utilitario.formatarParaMonetario(order.getProduto().getPreco() * order.getQuantidade())%></td>
                </tr>
                <%
                                                    classe = classe.equals("linha1") ? "linha2" : "linha1";
                                                }
                %>

                <tr>
                    <td style="border-bottom: medium none rgb(255, 255, 255);"></td>
                    <td style="border-bottom: medium none rgb(255, 255, 255);"></td>
                    <th><b>Subtotal</b></th>
                    <th>
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
                    <th colspan="2"><b>Frete </b></th>
                    <th><%=Utilitario.formatarParaMonetario((Double) session.getAttribute("valorCep"))%></th>
                </tr>

                <tr>
                    <td style="border-bottom: medium none rgb(255, 255, 255);"></td>
                    <td style="border-bottom: medium none rgb(255, 255, 255);"></td>
                    <th colspan="2"><b>Total</b></th>
                    <th><b>${valorTotalPedido}</b></th>
                </tr>
                <%
                                            } else {
                %>
                <tr>
                    <td colspan="6" style="border-bottom: medium none rgb(255, 255, 255);"><strong>Não consta nenhum registro!</strong></td>
                </tr>
                <%                            }
                            Cliente cliente = (Cliente) request.getAttribute("cliente");
                            Entrega entrega = (Entrega) request.getAttribute("entrega");
                %>
            </tbody>
        </table>
        <div class="push20"></div>
        <div id="dados" style="height:340px;">
            <div id="dadosUsuario" style="width:230px; margin-left:10px;">
                <span class="productDesc">Seus Dados</span>

                <div class="push15"></div>
                <p class="cart2"> <b>Nome</b><br> ${cliente.nomeCompleto}</p>
                <p class="cart2"><b>Endereço</b><br>Rua ${cliente.endereco.rua}</p>
                <p class="cart2"><b>Bairro</b><br>${cliente.endereco.bairro}</p>
                <p class="cart2"><b>Cidade</b><br>${cliente.endereco.cidade}</p>
                <p class="cart2"><b>UF</b><br>${cliente.endereco.estado}</p>
                <p class="cart2"><b>CEP</b><br><%=Utilitario.formatarString(cliente.getEndereco().getCep(), "#####-###")%></p>
                <p class="cart2"><b>Telefone</b><br><%=Utilitario.formatarString(cliente.getTelefone(), "(##) ####-####")%></p>
                <p class="cart2"><b>e-Mail</b><br>${cliente.email}<br></p>
            </div>
            <div id="dadosEntrega" style="width:230px; float:left; margin-left:10px;">
                <span class="productDesc">Dados para Entrega</span>
                <div class="push5"></div>
                <p class="cart2"> <b>Nome</b><br> ${entrega.nomeCompleto}</p>
                <p class="cart2"><b>Endereço</b><br>Rua ${entrega.endereco.rua}</p>
                <p class="cart2"><b>Bairro</b><br>${entrega.endereco.bairro}</p>
                <p class="cart2"><b>Cidade</b><br>${entrega.endereco.cidade}</p>
                <p class="cart2"><b>UF</b><br>${entrega.endereco.estado}</p>
                <p class="cart2"><b>CEP</b><br><%=Utilitario.formatarString(entrega.getEndereco().getCep(), "#####-###")%></p>
                <p class="cart2"><b>Telefone</b><br><%=Utilitario.formatarString(entrega.getTelefone(), "(##) ####-####")%></p>

            </div>
            <div id="formaPgto" style="width:230px; float:left; clear:none; margin-left:10px;">
                <span class="productDesc">Forma de Frete</span>
                <div class="push5"></div>
                <%
                            if (request.getAttribute("entrega") != null) {
                                
                                if (entrega.getFrete() == 0.00) {
                                    out.println("Entrega Normal:");
                                    out.println(Utilitario.formatarParaMonetario(entrega.getFrete()));
                                } else {
                                    out.println("Entrega Sedex:");
                                    out.println(Utilitario.formatarParaMonetario(entrega.getFrete()));
                                }

                            }
                %>
                <div class="push5"></div>
                <span class="productDesc">Dados Pagamento</span>
                <div class="push5"></div>
                <%
                            if (request.getAttribute("pagamento") != null) {
                                if (request.getAttribute("pagamento") instanceof PagamentoBoleto) {
                                    PagamentoBoleto pagamento = (PagamentoBoleto) request.getAttribute("pagamento");
                %>
                <p class="cart2"> <b>Número do Pedido:</b><br> ${pagamento.pedido.codigoPedido}</p>
                <p class="cart2"> <b>Número Documento:</b><br> ${pagamento.numeroDocumento}</p>
                <p class="cart2"> <b>Data Vencimeto:</b><br> <%=Utilitario.DataFormatada(pagamento.getVencimento())%></p>
                <p class="cart2"> <b>Cedente:</b><br> ${pagamento.cedente}</p>
                <p class="cart2"> <b>Valor Total do Pedido:</b><br> <%=Utilitario.formatarParaMonetario(pagamento.getValorTotal())%></p>                
                    <%
                                                    } else {
                                                        PagamentoCartao pagamento = (PagamentoCartao) request.getAttribute("pagamento");
                    %>
                <p class="cart2"> <b>Número do Pedido:</b><br> ${pagamento.pedido.codigoPedido}</p>
                <p class="cart2"> <b>Número:</b><br> ${pagamento.numeroCartao}</p>
                <p class="cart2"> <b>Validade:</b><br> ${pagamento.validadeCartao}</p>
                <p class="cart2"> <b>Bandeira:</b><br> ${pagamento.bandeira}</p>
                <p class="cart2"> <b>Verificado de segurança:</b><br> ${pagamento.codigoSeguranca}</p>
                <p class="cart2"> <b>Nome titular:</b><br> ${pagamento.nomeTitular}</p>
                <p class="cart2"> <b>Qtd de Parcelas:</b><br> ${pagamento.numeroParcelas}</p>
                <p class="cart2"> <b>Valor da Parcela:</b><br> <%=Utilitario.formatarParaMonetario(pagamento.getValorPacela())%></p>
                <p class="cart2"> <b>Valor Total do Pedido:</b><br> <%=Utilitario.formatarParaMonetario(pagamento.getValorTotal())%></p>
                    <%
                                    }
                                }
                                //removo os itens da sessão para evitar do cliente gravar mais de uma vez o mesmo pedido
                                session.removeAttribute("valorCep");
                                session.removeAttribute("carrinho");
                    %>

            </div>

        </div>

        <div class="push20"></div>
    </div>
</div>