<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="br.com.siscultbook.bean.Carrinho"%>
<%@page import="br.com.siscultbook.bean.Funcionario"%>
<%@page import="br.com.siscultbook.bean.Cliente"%>
<!--
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
-->
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<div id="topo">
    <div id="figuraTopo">
        <div id="nav-header">
            <span>versão: 1.0.0</span> |
            <a href="SisCultbookController?cmd=dadosCliente&amp;codigo=${codigoUsuario}" class="pequeno">Meu Cadastro</a> |
            <a href="SisCultbookController?cmd=finalizarCompra" class="pequeno">Finalizar Compra</a>
            |
            <span>
                <%
                    session = request.getSession(true);
                    if (session.getAttribute("usuario") != null) {
                        if (session.getAttribute("usuario") instanceof Cliente) {
                            Cliente cliente = (Cliente)session.getAttribute("usuario");
                            
                %>
                            Ol&aacute;! <%=cliente.getNomeCompleto() %>,
                            seu &uacute;ltimo acesso foi: <%=Utilitario.DataFormatadaComleta(cliente.getLoginCliente().getUltimoAcesso()) %>
            <%
                    }else{
                            Funcionario funcionario = (Funcionario)session.getAttribute("usuario");
                %>
                        Ol&aacute;! <%=funcionario.getNomeCompleto() %>,
                       seu &uacute;ltimo acesso foi: <%=Utilitario.DataFormatadaComleta(funcionario.getLoginFuncionario().getUltimoAcesso()) %>
                <%
                        }
                    }else{
                        out.println("Olá Visitante!");
                    }
                %>
            </span> |
            <a href="SisCultbookController?cmd=sairSistema" class="pequeno">Sair</a>

        </div>
        <div id="logo-header"><a href="index.jsp"><img src="images/logoCultbook.png" width="314" height="71" border="0" title="Casa Publicadora Brasileira"></a></div>
        <div id="cart-header">
            <div id="cart-ico">
                <a href="SisCultbookController?cmd=consultarItensCarrinho"><img src="images/carrinho.png" border="0" title="ir para meu carrinho de compra"></a>
          </div>
            <div id="cart-content">
                <a href="SisCultbookController?cmd=consultarItensCarrinho" class="cart" title="ir para meu carrinho de compra">Carrinho de Compras<br>
                    <span>Total de</span>
                    <% 
                        if (session.getAttribute("carrinho") == null) {
                            out.println("R$ 00,00");
                        }else{
                            if (session.getAttribute("carrinho") instanceof Carrinho) {
                                Carrinho carrinho = (Carrinho)session.getAttribute("carrinho");
                    %>
                    <%=Utilitario.formatarParaMonetario(carrinho.calcularTotalCarrinho()) %>
                    <%  }
                            }%>
                </a>
            </div>
        </div>

    </div>
    <div class="push1"></div>
    <!-- menu -->
    <div id="fundo_menu">
        <div id="menu">
            <ul>
                <li><a href="SisCultbookController?cmd=paginaPrincipal" title="Home">Home</a></li>
                <li><a href="SisCultbookController?cmd=dadosCliente&amp;codigo=${codigoUsuario}" title="Cadastro">Cadastro</a></li>
                <li><a href="SisCultbookController?cmd=consultarMeusPedidos" title="Meus Pedidos">Meus Pedidos</a></li>
                <li><a href="SisCultbookController?cmd=arquivosEstaticos&pg=fale" title="Lojas" target="_parent">Fale Conosco</a></li>
                <li class="last"><a href="SisCultbookController?cmd=areaRestrita" title="Restrito">&Aacute;rea Restrita</a></li>
            </ul>
            <form action="SisCultbookController?cmd=consultarLivrosTipo" method="post">
            	<fieldset style="background:none; float:left">
                   <label>
                        <select name="tipoPesquisa" id="tipoPesquisa" style="width:100px; background-color:#EFEFEF; border:none;">
                        		<option value="">Escolha... </option>
                                <option value="isbn">ISBN </option>
                                <option value="titulo">T&iacute;tulo </option>
                        </select>
                  </label>
                </fieldset>
                <fieldset>
                    <label>
                        <input name="busca" title="Faça sua pesquisa por título, autor, código do produto, ou parte da palavra desejada." type="text">
                    </label>
                </fieldset>
                <input value="" type="submit">
            </form>
        </div>
    </div>
    <!-- end menu -->
    <div class="push1"></div>
</div>

