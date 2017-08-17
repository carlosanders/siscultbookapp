<%@page import="br.com.siscultbook.bean.Autor"%>
<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="java.util.List, br.com.siscultbook.bean.Funcionario" %>
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
        <div id="erro" style="margin-left:15px;">
        	<font color="#FF0000" style="font-weight:bold;">${erro}</font>
            <br />
        	${mensagem}
        </div>
    <%--
        Página é visão que lista todos os autores cadastrados no banco de dados
    --%>
    <table width="710" border="0" align="center" cellpadding="0" cellspacing="0" id="table_eventos_cadastrados" style="margin-left:10px;">
        <thead>
            <tr>
                <th width="82">Cód.</th>
                <th width="340">Nome</th>
                <th width="209">Sobrenome</th>
                <th width="82">Buscar Livros</th>
            </tr>
        </thead>
        <%
                    String classe = "linha1";
                    List<Autor> autores = (List<Autor>) request.getAttribute("autores");
                    for (Autor autor : autores) {

        %>

        <tr class="<%=classe%>">
            <td width="82" align="center"><%=autor.getCodigoAutor()%></td>
        <td width="340" align="left"><%=autor.getNomeCompleto()%></td>
          <td width="209" align="left"><%=autor.getSobrenome()%></td>   
          <td width="82" align="center">
          <a href="SisCultbookController?cmd=consultarLivrosPorAutor&amp;codigo=<%=autor.getCodigoAutor()%>">
                    <img src="images/lc10853.png" alt="Listar Livros do Autor"  width="24" height="24" border="0" />
                </a>
          </td>     
        </tr>
        <%
                        classe = classe.equals("linha1") ? "linha2" : "linha1";
                    }
        %>
    </table>
</div>