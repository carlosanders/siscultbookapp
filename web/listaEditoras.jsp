<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="java.util.List, br.com.siscultbook.bean.Editora" %>
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
        	<font color="#FF0000" style="font-weight:bold">${erro}</font>
            <br />
        	${mensagem}
        </div>
    <%--
        Página é visão que lista todos os clientes cadastrados no banco de dados
    --%>
    <table width="710" border="0" align="center" cellpadding="0" cellspacing="0" id="table_eventos_cadastrados" style="margin-left:10px;">
        <thead>
            <tr>
                <th width="63" align="center">Cód.</th>
                <th width="301" align="center">Nome</th>
                <th width="260" align="center">OBS.</th>
                <th width="89" align="center">Buscar <br />
                Livros</th>
            </tr>
        </thead>
        <%
                    String classe = "linha1";
                    List<Editora> editoras = (List<Editora>) request.getAttribute("editoras");
                    for (Editora editora : editoras) {

        %>

        <tr class="<%=classe %>">
            <td width="63" align="center"><%=editora.getCodigoEditora()%></td>
        <td width="301" align="left"><%=editora.getNomeEditora()%></td>
          <td width="260" align="left"><%=editora.getObs()%></td>
          <td width="89" align="center">
          <a href="SisCultbookController?cmd=consultarLivrosPorEditora&amp;codigo=<%=editora.getCodigoEditora()%>">
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