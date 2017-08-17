<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>Painel de Controle</title>
        <link href="css/login.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div id="divTitulo">Sistema Cultbook</div>
        <div id="login">
            <form name="form1" action="SisCultbookController" method="post">
            <input type='hidden' name='cmd' value="acessarUsuario" />                
                <div id="erro">${mensagem}</div>
                <br />
                <label id="username">Usu&aacute;rio</label><br />
                <input class="user" type="text" name="login" value=""/>
                <label id="password">Senha</label><br />
                <input type="password" name="senha" value="" />
                <div class="fabrik_subelement" style="float: left; width: 130px; height:25px; text-align:center">                                	
                    <input id="cliente" class="radio" name="usuario" type="radio" value="cliente">
                    <label for="cliente" style="font-size:10pt;">Cliente</label>                
               </div>
               <div class="fabrik_subelement" style="float: left; width: 130px; height:25px;text-align:center">                                	
                    <input id="funcionario" class="radio" name="usuario" type="radio" value="funcionario">
                    <label for="funcionario" style="font-size:10pt;">funcion&aacute;rio</label>                
               </div>
                <input type="submit" name="submit" value="Entrar" class="submit" />
            </form>
        </div>
    </body>
</html>