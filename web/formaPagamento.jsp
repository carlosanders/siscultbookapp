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
        ${mensagem}
    </div>
    <div id="sobre" >

        <img src="images/carrinho2.png">
        <div class="push20"></div>

        <span class="shopDesc">Finalizando Compra.</span>

        <div class="push20"></div>

        <form name="entrega" action="#" method="post" onsubmit="return vali(this);">
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
                                if (request.getAttribute("itensCarrinho") != null) {
                                    List itemsOrdered = (List) request.getAttribute("itensCarrinho");
                                    Item order;
                                    //Produto livro;
                                    for (int i = 0; i < itemsOrdered.size(); i++) {
                                        order = (Item) itemsOrdered.get(i);
                                        //livro = (Livro) itemsOrdered.get(i);

                    %>

                    <tr>
                        <td width="90"><center>
                                <a href="SisCultbookController?cmd=detalhesProduto&cod=<%=order.getProduto().getCodigoProduto()%>">
                                    <img alt=""  src="imgensLivros/<%=order.getProduto().getFigura()%>">
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

                        <td width="85">${valorTotalCarrinho}</td>
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
                    <%                                }
                    %>
                </tbody>
            </table>
            <div class="push20"></div>
            <div id="dados">
                <div id="dadosUsuario">
                    <span class="productDesc">Seus Dados</span>
                    <div class="push5"></div>
                    <a href="#" class="remove">» Alterar Cadastro</a>
                    <div class="push15"></div>
                    <p class="cart2"> <b>Nome</b><br> ${cliente.nomeCompleto}</p>
                    <p class="cart2"><b>Endereço</b><br>Rua ${cliente.endereco.rua}</p>
                    <p class="cart2"><b>Bairro</b><br>${cliente.endereco.bairro}</p>
                    <p class="cart2"><b>Cidade</b><br>${cliente.endereco.cidade}</p>
                    <p class="cart2"><b>UF</b><br>${cliente.endereco.estado}</p>
                    <p class="cart2"><b>Telefone</b><br>&nbsp;${cliente.telefone}</p>
                    <p class="cart2"><b>e-Mail</b><br>${cliente.email}<br></p>
                </div>
                <div id="dadosEntrega">
                    <span class="productDesc">Dados para Entrega</span>
                    <div class="push5"></div>
                    <p class="cart2"> 
                        <input onclick="CopiaDados()" name="mesmo" type="checkbox" value="sim" />
			Clique ao lado, caso o endereço para seja o mesmo do cadastro
                    </p>
                    <br />
                    <p class="cart2">
                        <b>Nome</b><br>
                        <input name="nome_ent" maxlength="35" size="35" class="campo" />
                        <input name="nome" value="${cliente.nomeCompleto}" type="hidden" />
                    </p>
                    <p class="cart2">
                        <b><b>Rua</b> <br>
                            <input name="endereco_ent" maxlength="40" size="35" class="campo" />
                            <input name="endereco" value="${cliente.endereco.rua}" type="hidden" />
                        </b></p>

                    <p class="cart2"><b>Bairro</b><br>
                        <input name="bairro_ent" maxlength="30" size="35" class="campo" />
                        <input name="bairro" value="${cliente.endereco.bairro}" type="hidden" />
                    </p>

                    <p class="cart2"><b>Cidade</b><br>
                        <input name="cidade_ent" maxlength="30" class="campo" size="35" />
                        <input name="cidade" value="${cliente.endereco.cidade}" type="hidden" /><br>
                    </p>

                    <p class="cart2"><b>Estado</b><br>
                        <select class="campo" name="uf_ent">
                            <% for (Estados estado : Estados.values()) {%>
                            <% if (estado.equals(request.getAttribute("clienteEstado"))) {%>
                            <option value="<%=estado.name()%>" selected="selected" ><%=estado.name()%></option>
                            <% } else {%>
                            <option value="<%=estado.name()%>"><%=estado.name()%></option>
                            <% }%>
                            <% }%>
                        </select>
                    </p>

                    <p class="cart2"><b>CEP</b><br>
                        <input name="cep_ent" maxlength="30" class="campo" size="35" />
                        <input name="cep" value="" type="hidden" /><br>
                    </p>

                    <p class="cart2"><b>Telefone</b><br>
                        <input name="telefone_ent" maxlength="30" class="campo" size="35" />
                        <input name="telefone" value="" type="hidden" /><br>
                    </p>

                </div>

            </div>
            <div class="push15"></div>
            <div id="formaFrete">
                <span class="productDesc">Forma de Frete</span>
                <div class="push5"></div>
                <p class="cart">
                    <input name="ffrete" onclick="return validar_reload(entrega);" value="n" type="radio" />
                    <b> Entrega Normal = R$ 0,00</b> (10 a 15 dias úteis)<br>
                    <input onclick="return validar_cep(entrega);" id="radio2" name="ffrete" value="s" type="radio" />
                    <b> Entrega Sedex </b> = Após selecionar, aguarde a atualização. (3 a 7 dias úteis)
                </p>
            </div>

            <div id="formaPgto">
                <span class="productDesc">Formas de Pagamento</span>
                <div class="push5"></div>
                <p class="cart2">
                    <input id="radio1" onclick="document.getElementById('cartao').style.display='block';" value="1" name="fpagamento" type="radio">
                    <b> Cartão de Crédito </b>(selecione esta opção para digitar seu dados) <br>
                    <span class="warn"><b>&nbsp;&nbsp;- Não serão aceitos cartões de débito. </b></span>
                </p>
                <div id="cartao" >
                    <p class="cart2">Cartão de Crédito &nbsp;
                        <select name="tipo_cartao">
                            <option selected="selected" value="">Escolha aqui</option>
                            <% for (TipoCartao cartoes : TipoCartao.values()) {%>
                            <option value="<%=cartoes.name()%>"><%=cartoes.name()%></option>
                            <% }%>
                        </select>
                    </p>

                    <div class="push5"></div>

                    <p class="cart2">Parcelamento
                        <select name="parcelas">
                            <option selected="selected" value="1">1x de R$ 28,80</option>
                        </select>
                    </p>

                    <div class="push5"></div>
                    <p class="cart2">Nome do Titular&nbsp;
                        <input name="nome_cartao" maxlength="20" class="campo">
                    </p>

                    <div class="push5"></div>
                    <p class="cart2">Número do Cartão
                        <input name="num_cartao" class="campo" maxlength="22">
                    </p>

                    <div class="push5"></div>

                    <p class="cart2">Código Verificador
                        <input name="cod_verificador" class="campo" size="4" maxlength="4"><img src="images/cart2.gif" width="86" height="53">
                    </p>

                    <div class="push5"></div>
                    <p class="cart2">Data de Validade
                        <select name="mes_validade">
                            <option selected="selected" value="">Escolha o mês</option>
                            <option value="01">Janeiro</option>
                            <option value="02">Fevereiro</option>
                            <option value="03">Março</option>
                            <option value="04">Abril</option>
                            <option value="05">Maio</option>
                            <option value="06">Junho</option>
                            <option value="07">Julho</option>
                            <option value="08">Agosto</option>
                            <option value="09">Setembro</option>
                            <option value="10">Outubro</option>
                            <option value="11">Novembro</option>
                            <option value="12">Dezembro</option>
                        </select>

                        <select name="ano_validade">
                            <option selected="selected" value="">Escolha o ano</option>
                            <option value="2011">2011</option>
                            <option value="2012">2012</option>
                            <option value="2013">2013</option>
                            <option value="2014">2014</option>
                            <option value="2015">2015</option>
                            <option value="2016">2016</option>
                            <option value="2017">2017</option>
                            <option value="2018">2018</option>
                            <option value="2019">2019</option>
                            <option value="2020">2020</option>
                            <option value="2021">2021</option>
                        </select>
                    </p>


                </div>
                <p class="cart2">
                    <input id="radio1" onclick="document.getElementById('cartao').style.display='none';" value="2" name="fpagamento" type="radio"> 
                    <b>Boleto Bancário</b>
                </p>
                <span class="warn">&nbsp;&nbsp;- Transferência online (para clientes Itaú)<br>
                    &nbsp;&nbsp;- Impressão de Boleto Bancário online<br><br></span>                

            </div>
            <div class="push20"></div>
            <div id="dadosFinalizando">
                <center>
                    <div style="float: left;">
                        <a href="SisCultbookController?cmd=sairSistema"><img src="images/bt_cancel.png" border="0"></a>
                    </div>
                    <div style="float: right;">
                        <input src="images/bt_checkOut.png" border="0" type="image" />
                    </div>
                </center>    
            </div>
        </form>

    </div>
</div>