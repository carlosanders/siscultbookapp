<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.GregorianCalendar"%>
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
<%----%> 
<link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/screen.css" />
<div id="cadastros">
    <!-- path -->

    <div id="path">
        <ul>
            <li class="base">Você está aqui:</li>
            <li class="first">
                ${titulo} 
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
        <ol>
            <li><label for="descricao" class="error">Informe o campo desrcrição</label></li>
            <li><label for="statusPedido" class="error">Selecione o Status do Pedido</label></li>
            <li><label for="valorTotal" class="error">Informe um valor total para o campo</label></li>
            <li><label for="nrBoleto" class="error">Informe o nº do Boleto</label></li>
        </ol>
    </div>

    <%--
        esse form a action dele vai enviar para o SisCultbookController, ele vai receber e redirecionar para
        SisCultbookHelper e o helper vai capturar o valor do paramentro "cmd" input hidden
        de acordo com o parametro ele ira tratar essa requisição e devolver para o SisCultbookController
        com uma página de destino
    --%>
    <form name="form" id="form" action="SisCultbookController" method="post" >
        <input type='hidden' name='cmd' value="exibirPedidosEntreAsDatas" />
        <input type='hidden' name='codigo' value="${param.codigo}" />
        <input type='hidden' name='codigoCliente' value="${pedido.cliente.codigoCliente}" />
        <fieldset class="relatorios">
            <legend>Relat&oacute;rios Gerais do Sistema: </legend>
            <%
                        GregorianCalendar datahj = new GregorianCalendar();
                        datahj.setTime(new Date());
            %>
            
            <div>            	
                <label for="periodoInicial" >
                    Per&iacute;odo Inicial
                </label> <input id="calendarioinicial" name="calendarioinicial" value="<%=Utilitario.DataFormatada(datahj)%>" type="text" >
            </div>
            <div>            	
                <label for="periodoFinal" >
                    Per&iacute;odo Final
                </label> <input id="calendariofinal" name="calendariofinal" value="<%=Utilitario.DataFormatada(datahj)%>" type="text" class="{validate:{required:true}}">
            </div>
            <label for="listarLivros" >Arquivos
            <div id="relatorios">
          <ul id="painel">
                    <li>
<div class="balao2" ><a title="Consultar a posi&ccedil;&atilde;o de todos os pedidos" href="SisCultbookController?cmd=exibirPosicaoPedidos" accesskey="13" tabindex="13"><img title="Consultar a posi&ccedil;&atilde;o de todos os pedidos" alt="Consultar a posi&ccedil;&atilde;o de todos os pedidos" src="images/acroread2.png" width="64" height="64" border="0" /><br>
                                Posi&ccedil;&atilde;o Pedidos</a></div>
                    </li>
                    <li>
<div class="balao2" ><a title="Relat&oacute;rio de Livros dos Pedidos" href="SisCultbookController?cmd=exibirLivrosDosPedidos" accesskey="13" tabindex="13"><img title="Relat&oacute;rio de Livros dos Pedidos" alt="Relat&oacute;rio de Livros dos Pedidos" src="images/my_documents.png" width="64" height="64" border="0" /><br>
                                Livros Pedidos</a></div>
                    </li>
                    <li>
<div class="balao2" ><a title="Relat&oacute;rio de Livros cadastrados por assunto" href="SisCultbookController?cmd=exibirPosicaoLivros" accesskey="13" tabindex="13"><img title="Relat&oacute;rio de Livros cadastrados por assunto" alt="Relat&oacute;rio de Livros cadastrados por assunto" src="images/kviewshell.png" width="64" height="64" border="0" /><br>
                                Livros por Assunto</a></div>
                    </li>
          </ul>
          </div>
        </fieldset>
        <div>
            <button type="submit" id="submit-go"><p style="margin-top:-4px;">Pesquisar</p></button>
        </div>
        
        
        
    </form>
    
</div>