<%-- 
    Document   : pedido
    Created on : 16/03/2011, 17:43:01
    Author     : com1dn-711
--%>

<%@page import="java.util.Locale"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="br.com.siscultbook.bean.ProdutoCarrinho"%>
<%@page import="java.util.List"%>
<%@page import="br.com.siscultbook.bean.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<jsp:useBean id="carrinho" class="br.com.siscultbook.bean.Carrinho" scope="session" ></jsp:useBean>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="pedido" class="br.com.siscultbook.bean.Pedido">
            <jsp:setProperty name="pedido" property="*"/>
        </jsp:useBean>
        <%
                    if (pedido.getItemID() != null) {
                        if (pedido.getNumItens() == 0) {
                            carrinho.adicioneItem(pedido.getItemID());
                        } else {
                            carrinho.setQtdeItens(pedido.getItemID(), pedido.getNumItens());
                        }
                    }
        %>
        <h1 align="center">Estado do seu Pedido</h1>
        <%
                    List itemsOrdered = carrinho.getItemsCarrinho();
                    ProdutoCarrinho order;
                    NumberFormat formatter = NumberFormat.getCurrencyInstance(
                            new Locale("pt", "br"));
                    String formURL = response.encodeURL("/WebExemplos/pedido.jsp");
        %>
        <TABLE BORDER=1 ALIGN="CENTER">
            <TR BGCOLOR="#FFAD00">
                <TH>ID do Item<TH>Descrição<TH>Custo unitário<TH>Quantidade<TH>Custo total
                    <%-- Corpo da tabela --%>
                    <%
                                for (int i = 0; i < itemsOrdered.size(); i++) {
                                    order = (ProdutoCarrinho) itemsOrdered.get(i);
                    %>
            <TR>
                <TD><%=order.getProdutoID()%>
                <TD><%=order.getDescricaoCurta()%>
                <TD><%=formatter.format(order.getPrecoUnitario())%>
                <TD>
                    <FORM ACTION="<%=formURL%>">
                        <INPUT TYPE="HIDDEN" NAME="itemID" VALUE="<%=order.getProdutoID()%>">
                        <INPUT TYPE="TEXT" NAME="numItens" SIZE="3"
                               VALUE="<%=order.getNumProdutos()%>">
                        <SMALL>
                            <INPUT TYPE="SUBMIT" VALUE="Atualizar pedido">
                        </SMALL>
                    </FORM>
                <TD><%=formatter.format(order.getPrecoTotal())%>
                    <%}%>
                    <%-- Fim do for --%>
        </TABLE>
        <%-- Finalizar compra --%>
        <% String checkoutURL = response.encodeURL("/WebExemplos/finaliza.jsp");%>
        <FORM ACTION="<%=checkoutURL%>">
            <BIG><CENTER>
                    <INPUT TYPE="SUBMIT" VALUE="Finalizar compra">
                </CENTER></BIG>
        </FORM>
        <%-- Continuar compra --%>
        <%String continuar = response.encodeURL("/WebExemplos/catalogo.jsp");%>
        <FORM ACTION="<%=continuar%>">
            <BIG><CENTER>
                    <INPUT TYPE="SUBMIT" VALUE="Continuar comprando">
                </CENTER></BIG>
        </FORM>

    </body>
</html>