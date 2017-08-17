<%@page import="br.com.siscultbook.util.Utilitario"%>
<%@page import="java.util.List, br.com.siscultbook.bean.Cliente" %>

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
    <table width="710" border="0" align="center" cellpadding="0" cellspacing="0" id="table_eventos_cadastrados">
        <thead>
            <tr>
                <th align="center" valign="middle">Cód.</th>
              <th align="center" valign="middle">CPF</th>
              <th align="center" valign="middle">Nome</th>
              <th align="center" valign="middle">Telefone</th>
              <th align="center" valign="middle">Status</th>
              <th align="center" valign="middle">U. Acesso</th>
                <th colspan="2" align="center">Editar</th>
            </tr>
        </thead>
        <%
                    String classe = "linha1";
                    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                    for (Cliente cliente : clientes) {

        %>

        <tr class="<%=classe %>">
            <td align="center" valign="middle"><%=cliente.getCodigoCliente()%></td>
          <td align="center" valign="middle"><%=Utilitario.formatarString(cliente.getCpf(), "###.###.###-##")%></td>
          <td align="center" valign="middle"><%=cliente.getNomeCompleto()%></td>
          <td align="center" valign="middle"><%=Utilitario.formatarString(cliente.getTelefone(), "(##) ####-####")%></td>
          <td align="center" valign="middle"><%=cliente.getLoginCliente().getStatus()%></td>
          <td align="center" valign="middle"><%=Utilitario.DataFormatada(cliente.getLoginCliente().getUltimoAcesso())%></td>
            <td align="center" valign="middle"><a class="item-excluir" id="<%=cliente.getCodigoCliente()%>" href="#"><img src="images/error.png" alt="Apagar Notícias" width="15" height="14" border="0" /></a></td>
            <td align="center" valign="middle"><a href="SisCultbookController?cmd=editarCliente&amp;codigo=<%=cliente.getCodigoCliente()%>"><img src="images/b_edit.png" alt="Editar Notícias"  width="16" height="16" border="0" /></a></td>
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
						endereco	: 'SisCultbookController?cmd=excluirCliente&codigo='+codigo
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