<%@page import="br.com.siscultbook.bean.Pedido"%>
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
        <br />
        ${mensagem}
    </div>
    <%--
        Página é visão que lista todos os clientes cadastrados no banco de dados
    --%>
    <%
                String classe = "linha1";
                if (request.getAttribute("pedidos") != null) {
    %>
    <table width="710" border="0" align="center" cellpadding="0" cellspacing="0" id="table_eventos_cadastrados" style="margin-left:10px;">
        <thead>
            <tr>
                <th width="55" align="center" valign="middle">Cód.</th>
              <th width="272" align="center" valign="middle">Descrição</th>
              <th width="282" align="center" valign="middle">Status</th>
                <th width="104" align="center" valign="middle">Detalhes do Pedido</th>
            </tr>
        </thead>
        <%
                                List<Pedido> pedidos = (List<Pedido>) request.getAttribute("pedidos");
                                for (Pedido pedido : pedidos) {

        %>

        <tr class="<%=classe%>">
            <td width="55" align="center" valign="middle"><%=pedido.getCodigoPedido()%></td>
            <td width="272" align="left" valign="middle"><%=pedido.getDescricao()%></td>
            <td width="282" align="center" valign="middle"><%=pedido.getStatusPedido().name()%></td>
            <td width="104" align="center" valign="middle">
                <a href="SisCultbookController?cmd=exibirItensPedido&amp;codigo=<%=pedido.getCodigoPedido()%>">
                    <img src="images/lc10853.png" alt="Listar Itens do Pedido"  width="24" height="24" border="0" />
                </a>
            </td>
        </tr>
        <%
                                    classe = classe.equals("linha1") ? "linha2" : "linha1";
                                }
        %>
    </table>
<%
                        } else {
    %>
    <div class="push20"></div>
    <center>
    <strong style="color:#F00; text-align:center">Voc&ecirc; n&atilde;o possui pedidos ou n&atilde;o est&aacute; logado no sistema</strong>
    </center>
    <div class="push20"></div>
    <%                }
    %>

</div>