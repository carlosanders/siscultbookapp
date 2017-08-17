<%@page import="br.com.siscultbook.bean.Assunto"%>
<link href="cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="css/screen.css" />
<%----%>
<div id="cadastros">
  
  <!-- path -->
  
  <div id="path">
    <ul>
      <li class="base">Você está aqui:</li>
      <li class="first">${titulo}</li>
    </ul>
  </div>
  <!-- end path -->
  <div id="erro"> <font color="#FF0000" style="font-weight:bold">${erro}</font> <br />
    ${mensagem} </div>
  <%--
        Página é visão que lista todos os clientes cadastrados no banco de dados
    --%>
  <form name="form" id="form" action="SisCultbookController?cmd=consultarLivrosPorAssunto" method="post">
    <div>
      <label for="assuntoLivro">Lista de Assuntos</label>
      <select name="assuntoLivro" id="assuntoLivro" style="width:200px;">
        <option value="" selected="selected">Escolha o Assunto</option>
        <% for (Assunto assunto : Assunto.values()) {%>
        <option value="<%=assunto.name()%>"><%=assunto.name()%></option>
        <% }%>
      </select>
    </div>
    
    <div style="margin-top:30px; text-align:center">
      <button type="submit" id="submit-go">
      <p style="margin-top:-4px;">Buscar</p>
      </button>
    </div>
  </form>
</div>
    <script type="text/javascript">
        $(document).ready(function(){
            $('#submit-go').m2brDialog({
                tipo			: 'erro',
                titulo			: 'Erro!',
                texto			: 'Por favor, selecione o campo Assunto, para buscar os livros!',
                condicao		: {
                    origem		:	function() { return ($('#assuntoLivro').val() ? true : false); },
                    retorno		:	function() { return true; }
                }
            });
        });//fim da funcao
    </script>