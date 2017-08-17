<%@page import="br.com.siscultbook.bean.TipoCartao"%>
<%@page import="br.com.siscultbook.bean.PagamentoBoleto"%>
<%@page import="br.com.siscultbook.bean.EstadoDoPedido"%>
<%@page import="java.util.List"%>
<%@page import="br.com.siscultbook.bean.Sexo"%>
<%@page import="br.com.siscultbook.bean.Estados"%>
<%@page import="br.com.siscultbook.bean.Status"%>
<%@page import="br.com.siscultbook.bean.NivelDeAcesso"%>
<%@page import="br.com.siscultbook.bean.EstadoCivil"%>

<%
            request.setAttribute("situacaoCivil", EstadoCivil.values());
%>
 <script type="text/javascript">
	$(document).ready(function(){
		$("#telContato").mask("(99) 9999-9999");
		$("#cep").mask("99999-999");
	});
</script>  
<%----%> 
<link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/screen.css" />

<div id="cadastros">
    <!-- path -->

    <div id="path">
        <ul>
            <li class="base">Você está aqui:</li>
            <li class="first">
                ${titulo} n&ordm;:
                <font color="#FF0000"> ${pedido.codigoPedido}</font>
                <a href="SisCultbookController?cmd=exibirItensPCliente&amp;codigoPedido=${pedido.codigoPedido}" title="Listar Itens do Pedido">
                    <img src="images/lc10853.png" alt="Listar Itens do Pedido"  width="24" height="24" border="0" align="absmiddle" />
                </a>
            </li>

        </ul>
    </div>
    <!-- end path -->

    <div id="erro">
        <font color="#FF0000" style="font-weight:bold">${erro}</font>
        <br />
        ${mensagem}        
    </div>
    <!-- our error container -->
    <div class="erros">
        <h4>Acertes o erros para prosseguir:</h4>
        <ul class="disc">
            <li><label for="descricao" class="error">Informe o campo desrcrição</label></li>
            <li><label for="statusPedido" class="error">Selecione o Status do Pedido</label></li>
            <li><label for="valorTotal" class="error">Informe um valor total para o campo</label></li>
            <li><label for="nrBoleto" class="error">Informe o nº do Boleto</label></li>
            <li><label for="vencimento" class="error">Informe a data de vencimento v&aacute;lida</label></li>
            <li><label for="cedente" class="error">Informe o cedente do Boleto</label></li>
            <li><label for="nrCartao" class="error">Informe o nº Cartao</label></li>
            <li><label for="validadeCartao" class="error">Informe a validade do cartão</label></li>
            <li><label for="tipoCartaoBandeira" class="error">Selecione o tipo de cartão</label></li>
            <li><label for="codSeguranca" class="error">Informe o cód. segurança</label></li>
            <li><label for="titularCartao" class="error">Informe o nome titular</label></li>
            <li><label for="qtdParcelasCartao" class="error">Informe a quantidade de Parcelas</label></li>
            <li><label for="valorParcelaCartao" class="error">Informe o valor da parcela</label></li>
            <li><label for="telContato" class="error">Informe o telefone Contato</label></li>
            <li><label for="frete" class="error">Informe o valor do frete</label></li>
            <li><label for="nomeCompleto" class="error">Informe o nome do contato</label></li>
            <li><label for="rua" class="error">Informe a rua!</label></li>
            <li><label for="bairro" class="error">Informe o bairro!</label></li>
            <li><label for="cidade" class="error">Informe a cidade!</label></li>
            <li><label for="cep" class="error">Informe o cep!</label></li>
            <li><label for="estado" class="error">Informe o estado!</label></li>
        </ul>
    </div>

    <%--
        esse form a action dele vai enviar para o SisCultbookController, ele vai receber e redirecionar para
        SisCultbookHelper e o helper vai capturar o valor do paramentro "cmd" input hidden
        de acordo com o parametro ele ira tratar essa requisição e devolver para o SisCultbookController
        com uma página de destino
    --%>
    <form name="form" id="form" action="SisCultbookController" method="post" >
        <input type='hidden' name='cmd' value="atualizarPedido" />
        <input type='hidden' name='codigo' value="${param.codigo}" />
        <input type='hidden' name='codigoCliente' value="${pedido.cliente.codigoCliente}" />
        <fieldset class="pedido">
            <legend>Informa&ccedil;&otilde;es do Pedido n&ordm;: ${pedido.codigoPedido}</legend>
            <div>
                <label for="codigoPedido">C&oacute;d. Pedido</label>
                <input id="codigoPedido" name="codigoPedido" value="${pedido.codigoPedido}" type="text" readonly="readonly">
            </div>

            <div>            	
                <label for="descricao" >
                    Descri&ccedil;&atilde;o
                </label> <input id="descricao" name="descricao" value="${pedido.descricao}" type="text" class="{validate:{required:true}}">
            </div>
            <div>            	
                <label for="dataPedido" >
                    Data Pedido
                </label> <input id="dataPedido" name="dataPedido" value="${pedidoDataPedido}" type="text" class="{validate:{required:true,dateBR:true}}">
            </div>
            <div>
                <label for="statusPedido">Statuso do Pedido</label>
                <select name="statusPedido" id="statusPedido" class="fabrikinput inputbox {validate:{required:true}}">
                    <option value="" >Selecione...</option>
                    <% for (EstadoDoPedido status : EstadoDoPedido.values()) {%>
                    <% if (status.name().equals(request.getAttribute("pedidoStatus"))) {%>
                    <option value="<%=status.name()%>" selected="selected" ><%=status.name()%></option>
                    <% } else {%>
                    <option value="<%=status.name()%>"><%=status.name()%></option>
                    <% }%>
                    <% }%>
                </select>
            </div>           
        </fieldset>
        <fieldset class="pagamento">
            <legend>Informa&ccedil;&otilde;es de Pagamento</legend>
            
            <div>
                <label for="codPagamento">C&oacute;d. Pagamento</label> <input id="codPagamento" name="codPagamento" value="${pagamento.codigoPagamento}" type="text"  readonly="readonly" />
            </div>

            <div>
                <label for="valorTotal">Valor Total (R$)</label> <input id="valorTotal" name="valorTotal" value="${pagamentoTotal}" type="text" class="{validate:{required:true}}">
            </div>
            <%
                        if (request.getAttribute("pagamento") != null) {
                            if (request.getAttribute("pagamento") instanceof PagamentoBoleto) {
								
            %>
            <input type='hidden' name='fPagamento' value="boleto" readonly="readonly" />
            <div>
                <label for="nrBoleto">N&ordm; Boleto</label> <input id="nrBoleto" name="nrBoleto" value="${pagamento.numeroDocumento}" type="text" class="{validate:{required:true}}">
            </div>
            <span style="font-size:11px; margin-left:150px;">ex: 12/12/1900 </span>
            <div>
                <label for="vencimento">Vencimento</label>
                <input id="vencimento" name="vencimento" value="${pagamentoBoletoVencimento}" type="text" class="{validate:{required:true,dateBR:true}}">
            </div>
            <div>
                <label for="cedente">Cedente</label> <input id="cedente" name="cedente" value="${pagamento.cedente}" type="text" class="{validate:{required:true}}">
            </div>
            <%              } else {
            %>
            <input type='hidden' name='fPagamento' value="cartao" readonly="readonly" />
            <div>
                <label for="nrCartao">N&ordm; Cart&atilde;o</label>
                <input id="nrCartao" name="nrCartao" value="${pagamento.numeroCartao}" type="text" class="{validate:{required:true}}">
            </div>
            <div>
                <label for="validadeCartao">Validade do Cart&atilde;o</label>
                <input id="validadeCartao" name="validadeCartao" value="${pagamento.validadeCartao}" type="text" class="{validate:{required:true}}">
            </div>
            <div>
                <label for="tipoCartaoBandeira">Bandeira</label>
                <select name="tipoCartaoBandeira" id="tipoCartaoBandeira" class="fabrikinput inputbox {validate:{required:true}}">
                    <option value="" >Selecione...</option>
                    <% for (TipoCartao cartao : TipoCartao.values()) {%>
                    <% if (cartao.name().equals(request.getAttribute("pagamentoBandeira"))) {%>
                    <option value="<%=cartao.name()%>" selected="selected" ><%=cartao.name()%></option>
                    <% } else {%>
                    <option value="<%=cartao.name()%>"><%=cartao.name()%></option>
                    <% }%>
                    <% }%>
                </select>
            </div>
            <div>
                <label for="codSeguranca">C&oacute;d. Seguran&ccedil;a</label>
                <input id="codSeguranca" name="codSeguranca" value="${pagamento.codigoSeguranca}" type="text" class="{validate:{required:true}}">
            </div>
            <div>
                <label for="titularCartao">Titular Cart&atilde;o</label>
                <input id="titularCartao" name="titularCartao" value="${pagamento.nomeTitular}" type="text" class="{validate:{required:true}}">
            </div>
            <div>
                <label for="qtdParcelasCartao">Qtd Parcelas</label>
                <input id="qtdParcelasCartao" name="qtdParcelasCartao" value="${pagamento.numeroParcelas}" type="text" class="{validate:{required:true}}">
            </div>
            <div>
                <label for="valorParcelaCartao">Valor da Parcela (R$)</label>
                <input id="valorParcelaCartao" name="valorParcelaCartao" value="${pagamentoValorParcela}" type="text" class="{validate:{required:true}}">
            </div>

            <%
                            }
                        }
            %>
        </fieldset>
        <fieldset class="entrega">
            <legend>Informa&ccedil;&otilde;es da Entrega</legend>
            <div>
                <label for="codigoEntrega">C&oacute;d. Entrega</label>
                <input id="codigoEntrega" name="codigoEntrega" value="${entrega.codigoEntrega}" type="text" readonly="readonly" />
            </div>

            <div>            	
                <label for="telContato" >
                    Telefone Contato
                </label> <input id="telContato" name="telContato" value="${entrega.telefone}" type="text" class="{validate:{required:true}}">
            </div>
            <div>
                <label for="frete" >
                    Frete (R$)
                </label> <input id="frete" name="frete" value="${entregaFrete}" type="text" class="{validate:{required:true}}">
            </div>
            <div>
                <label for="nomeCompleto" >
                    Nome Contato
                </label> <input id="nomeCompleto" name="nomeCompleto" value="${entrega.nomeCompleto}" type="text" class="{validate:{required:true}}">
            </div>
            <div>
                <label for="rua">Rua</label> <input id="rua" name="rua" value="${entrega.endereco.rua}" type="text" class="{validate:{required:true}}">
            </div>
            <div>
                <label for="bairro">Bairro</label> <input id="bairro" name="bairro" value="${entrega.endereco.bairro}" type="text" class="{validate:{required:true}}">
            </div>
            <div>
                <label for="cidade">Cidade</label> <input id="cidade" name="cidade" value="${entrega.endereco.cidade}" type="text" class="{validate:{required:true}}">
            </div>
            <span style="font-size:11px; margin-left:150px;">ex: 21210000 </span>
            <div>
                <label for="cep">CEP</label> <input id="cep" name="cep" value="${entrega.endereco.cep}" type="text" class="{validate:{required:true}}">
            </div>

            <div>
                <label for="estado">Estado</label>
                <select name="estado" id="estado" class="fabrikinput inputbox {validate:{required:true}}">
                    <option value="" selected="selected">Escolha o estado</option>
                    <% for (Estados estado : Estados.values()) {%>
                    <% if (estado.equals(request.getAttribute("entregaEstado"))) {%>
                    <option value="<%=estado.name()%>" selected="selected" ><%=estado.name()%></option>
                    <% } else {%>
                    <option value="<%=estado.name()%>"><%=estado.name()%></option>
                    <% }%>
                    <% }%>
                </select>
            </div>          
        </fieldset>

        <div>
            <button type="submit" id="submit-go"><p style="margin-top:-4px;">Atualizar</p></button>
        </div>
    </form>

</div>