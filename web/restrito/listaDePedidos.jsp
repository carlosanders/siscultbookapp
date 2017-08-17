<%@page import="java.util.List"%>
<%@page import="br.com.siscultbook.bean.Pedido"%>
<%@page import="br.com.siscultbook.util.Utilitario"%>
<!--
<link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/consultaCliente.css" />
-->        
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
        <br />
        ${mensagem}
    </div>
    <%--
        Página é visão que lista todos os clientes cadastrados no banco de dados
    --%>
    <table width="710" border="0" align="center" cellpadding="0" cellspacing="0" id="table_eventos_cadastrados" style="margin-left:10px;">
        <thead>
            <tr style="background-color:#999">
                <th width="49" align="center" valign="middle">Cód.</th>
                <th width="143" align="center" valign="middle">Data do Pedido</th>
                <th width="259" align="center" valign="middle">Nome Completo</th>
                <th width="196" align="center" valign="middle">Status</th>
                <th align="center" valign="middle">Editar</th>
            </tr>
        </thead>
        <%
                    String classe = "linha1";
                    List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");                    
                    if (!pedidos.isEmpty()) {
                        for (Pedido pedido : pedidos) {

        %>

        <tr class="<%=classe%>">
            <td align="center" valign="middle"><%=pedido.getCodigoPedido()%></td>
            <td align="center" valign="middle"><%=Utilitario.DataFormatada(pedido.getDataPedido())%></td>
            <td align="center" valign="middle"><%=pedido.getCliente().getNomeCompleto()%></td>
            <td align="center" valign="middle"><%=pedido.getStatusPedido().name()%></td>
            <td align="center" valign="middle">              
                <a href="SisCultbookController?cmd=exibirPedido&amp;codigo=<%=pedido.getCodigoPedido()%>">
                    <img src="images/b_edit.png" alt="Editar Pedido"  width="16" height="16" border="0" /></a></td>
        </tr>
        <%
                                    classe = classe.equals("linha1") ? "linha2" : "linha1";
                                }

        %>
        <%
                            } else {
        %>
        <tr class="<%=classe%>">
            <td colspan="5">
                <center>
                    <strong style="color:#F00; text-align:center">O Sistema n&atilde;o encontrou nenhum pedido entre - ${dataInicial} à ${dataFinal} </strong>
                </center>
            </td>
        </tr>
        <% }%>
    </table>

</div>