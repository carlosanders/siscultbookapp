<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="br.com.siscultbook.bean.Funcionario"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>JSP Page</title>
        <link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" type="text/css" href="../css/screen.css" />
        <script type="text/javascript" src="scripts/jquery-1.6.1.min.js"></script>

    </head>
    <body>
        <div id="geral">
            <jsp:include page="cabecalho.jsp" />

            <div id="box-content">
                <jsp:include page="menuRestrito.jsp" />
                <div id="teste"  style="width:750px; float:left;">
                    <div id="path">
                        <ul>
                            <li class="base">Você está aqui:</li>
                            <li class="first">${titulo}</li>

                        </ul>
                    </div>
                    <%
                                session = request.getSession(true);
                                if (session.getAttribute("usuario") != null) {
                                    Funcionario funcionario = (Funcionario) session.getAttribute("usuario");

                    %>
                    <div id="ola" style="text-align: center">
                        Ol&aacute;! seja Bem-Vindo ! <font color="#990000"><em><%=funcionario.getNomeCompleto()%></em></font><br />
                        seu &uacute;ltimo acesso foi: <%=Utilitario.DataFormatadaComleta(funcionario.getLoginFuncionario().getUltimoAcesso())%>
                    <% }%>
                    </div>
                </div>
            </div>

            <div id="box-bottom"></div>
            <jsp:include page="rodape.jsp" />
        </div>
    </body>
</html>


