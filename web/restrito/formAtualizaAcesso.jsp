<%@page import="java.util.Collections"%>
<%@page import="java.util.Collection"%>
<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="java.util.List, br.com.siscultbook.bean.Acesso" %>
<jsp:useBean id="perfil" scope="request" class="br.com.siscultbook.bean.Acesso"></jsp:useBean>
<link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/consultaCliente.css" />
<link rel="stylesheet" type="text/css" href="../css/formAtualizaAcesso.css" />
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

    <form name="form" id="form" action="SisCultbookController" method="post" >
        <input type='hidden' name='cmd' value="atualizarAcesso" />
        <%--${param.codigo} aqui eu busco o objeto param da requisição por isso vem o objeto codigo --%>
        <input type='hidden' name='codigo' value="${perfil.nivelDeAcesso.codigoNivelDeAcesso}" />

        <fieldset class="tablet">
            <legend>N&iacute;vel de Acesso</legend>
            <div>
                <label for="nomeAcesso">Nome do Acesso:</label> <input id="nomeAcesso" name="nomeAcesso" value="${perfil.nivelDeAcesso.nivelDeAcesso}" type="text" readonly="readonly">
            </div>
        </fieldset>
        <fieldset class="acessos">
            <legend>Rela&ccedil;&atilde;o de Acessos do Sistema</legend>
            <%
                        Collection<Acesso> acessos = (Collection<Acesso>) request.getAttribute("acessos");
                        for (Acesso acesso : acessos) {
                            System.out.println("Ordem Resultado - " + acesso.getCodigoAcesso());
            %>
            <div class="fabrik_subelement" style="float: left; width: 250px; clear:none">
                <label style="width:200px; float:left">
                    <input id="<%=acesso.getCodigoAcesso()%>" class="fabrikinput checkbox" style="width:25px;"
                           name="<%=acesso.getCodigoAcesso()%>"
                           type="checkbox" <%=(acesso.getNivelDeAcesso().getCodigoNivelDeAcesso() == perfil.getNivelDeAcesso().getCodigoNivelDeAcesso() ? "checked":"") %> >
                    <span style="text-align:left; width:150px;"><%=acesso.getDescricao() %></span>
                </label>
            </div>
            <% }%>
        </fieldset>
        <div class="push10"></div>
        <div style="clear:both;">
            <button type="submit" id="submit-go"><p style="margin-top:-4px;">Atualizar</p></button>
        </div>
    </form>

</div>