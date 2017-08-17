<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="java.util.List, br.com.siscultbook.bean.Acesso" %>
<link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />

<link rel="stylesheet" type="text/css" href="../css/consultaCliente.css" />
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
    <div id="tst" style="text-align:center;">
    <table style="width:715px; margin-left:10px;" width="715" border="0" align="center" cellpadding="0" cellspacing="0" id="table_eventos_cadastrados">
        <thead>
            <tr>
                <th width="75" align="center" valign="middle">Código</th>
                <th width="337" align="center" valign="middle">Perfil</th>
                <th width="138" align="center" valign="middle">Acessos <br />
              Autorizados</th>
                <th colspan="2" align="center" valign="middle">Editar</th>
            </tr>
        </thead>
        <%
                    String classe = "linha1";
                    List<Acesso> acessos = (List<Acesso>) request.getAttribute("nivelDeAcesso");
                    for (Acesso acesso : acessos) {

        %>

        <tr class="<%=classe %>">
            <td align="center"><%=acesso.getNivelDeAcesso().getCodigoNivelDeAcesso() %></td>
            <td align="left"><%=acesso.getNivelDeAcesso().getNivelDeAcesso() %></td>
            <td align="center"><a href="SisCultbookController?cmd=autorizarAcesso&amp;codigo=<%=acesso.getNivelDeAcesso().getCodigoNivelDeAcesso() %>"><img src="images/lock_off.png" alt="Editar Nivel de Acesso"  width="24" height="24" border="0" /></a></td>
            <td width="118" align="center">
            <a class="item-excluir" id="<%=acesso.getNivelDeAcesso().getCodigoNivelDeAcesso() %>" href="#">
            <img src="images/error.png" alt="Apagar Nivel de Acesso" width="15" height="14" border="0" />
            </a>
            </td>
            <td width="45" align="center"><a href="SisCultbookController?cmd=editarNivelAcesso&amp;codigo=<%=acesso.getNivelDeAcesso().getCodigoNivelDeAcesso() %>"><img src="images/b_edit.png" alt="Editar Nivel de Acesso"  width="16" height="16" border="0" /></a></td>
        </tr>
        <%
                        classe = classe.equals("linha1") ? "linha2" : "linha1";
                    }
        %>
    </table>
    <script type="text/javascript">	
	$(document).ready(function(){
		$('table#table_eventos_cadastrados a.item-excluir').each(function(){
			var codigo = $(this).attr('id');
			$(this).m2brDialog({
				tipo: 		'pergunta',
				titulo:		'Confirme',
				texto:		'Tem certeza que deseja excluir o registro ID <span style="color:#F00">'+codigo+'</span>?',
				draggable: true,
				botoes: {
					1: {
						label		: 'sim',
						tipo		: 'link',
						endereco	: 'SisCultbookController?cmd=excluirNivelAcesso&codigo='+codigo
					},
					2: {
						label		: 'n&atilde;o',
						tipo		: 'fechar'
					},
				}
			});	
		});		
	});
	
</script>
</div>


</div>