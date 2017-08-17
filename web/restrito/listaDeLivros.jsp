<%@page import="br.com.siscultbook.bean.Livro"%>
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
            <li class="first">${titulo}: <font color="#FF0000">${totalDeLivros} produtos cadastrados!</font></li>

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
                
              <th width="113" align="left" valign="middle">ISBN</th>
              <th width="310" align="left" valign="middle">Título</th>
              
              <th width="163" align="left" valign="middle">Editora</th>
            <th width="77" align="center" valign="middle">Preço</th>
                <th colspan="2" align="center" valign="middle">Editar</th>
            </tr>
        </thead>
        <%
                    String classe = "linha1";
                    List<Livro> livros = (List<Livro>) request.getAttribute("livros");
                    for (Livro livro : livros) {


        %>

        <tr class="<%=classe%>">
        
          <td align="left" valign="middle"><%=livro.getIsbn()%></td>
          <td align="left" valign="middle"><%=livro.getTitulo()%></td>
          
          <td align="left" valign="middle"><%=livro.getEditora().getNomeEditora()%></td>
        <td align="center" valign="middle"><%=Utilitario.formatarParaMonetario(livro.getPreco()) %></td>
            <td width="23" align="center" valign="middle">
            <a class="item-excluir" id="<%=livro.getCodigoProduto()%>" href="#">
            <img src="images/error.png" alt="Apagar Livro" width="15" height="14" border="0" />
            </a>
            </td>
            <td width="27" align="center" valign="middle"><a href="SisCultbookController?cmd=editarLivro&amp;codigo=<%=livro.getCodigoProduto()%>"><img src="images/b_edit.png" alt="Editar Livro"  width="16" height="16" border="0" /></a></td>
      </tr>
      <%
                        classe = classe.equals("linha1") ? "linha2" : "linha1";
                    }

                    try {
                        Integer anterior;
                        Integer pagina = (Integer) request.getAttribute("pagina");
                        Integer limite = (Integer) request.getAttribute("limite");
                        Integer numOfPages = (Integer) request.getAttribute("numOfPages");
                        Integer totalDeLinhas = (Integer) request.getAttribute("totalDeLinhas");


        %>
        <tr class="paginacao">
            <td colspan="8" align="center" valign="middle">
          <%
                                if (pagina != 1) {
                                    anterior = pagina - 1;
                                    out.println("<a href=SisCultbookController?cmd=consultarLivro&pagina=" + anterior + " class='pequeno'>" + limite + " Anteriores</a>");
                                } else {
                                    out.println(limite + " Anteriores ");
                                }


                                int i;

                                for (i = 1; i <= numOfPages; i++) {
                                    if (i == pagina) {
                                        out.println("<b>" + i + "</b> ");
                                    } else {
                                        out.println("<a href=SisCultbookController?cmd=consultarLivro&pagina=" + i + " class='pequeno' >" + i + "</a> ");
                                    }
                                }

                                if ((totalDeLinhas % limite) != 0) {
                                    if (i == pagina) {
                                        out.println(i + " ");
                                    } else {
                                        out.println("<a href=SisCultbookController?cmd=consultarLivro&pagina=" + i + " class='pequeno'>" + i + "</a> ");
                                    }
                                }

                                int proxima;
                                if ((totalDeLinhas - (limite * pagina)) > 0) {
                                    proxima = pagina + 1;

                                    out.println("<a href=SisCultbookController?cmd=consultarLivro&pagina=" + proxima + " class='pequeno' >Próximos " + limite + "</a>");
                                } else {
                                    out.println("Próximos " + limite);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                                out.println("erro: " + e.getMessage());
                            }


                %></td>
        </tr>        
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
						endereco	: 'SisCultbookController?cmd=excluirLivro&codigo='+codigo
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