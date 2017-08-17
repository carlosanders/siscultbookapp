<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="pt-br" lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>JSP Page</title>
        <link href="cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
        <link rel="stylesheet" type="text/css" href="css/formAtualizaAcesso.css" />

    </head>
    <body>
        <div id="geral">
            <jsp:include page="cabecalho.jsp" />

            <div id="box-content">
                <jsp:include page="menuRestrito.jsp" />
                <div id="teste"  style="width:750px; float:left;">

                    <jsp:include page="formAtualizaAcesso.jsp" />

                </div>

            </div>

            <div id="box-bottom"></div>
            <jsp:include page="rodape.jsp" />
        </div>
    </body>
</html>


