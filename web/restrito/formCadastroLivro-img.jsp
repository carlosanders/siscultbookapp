<%@page import="br.com.siscultbook.bean.Status"%>
<%@page import="java.util.Collections"%>
<%@page import="br.com.siscultbook.bean.Assunto"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.Set"%>
<%@page import="br.com.siscultbook.bean.Autor"%>
<%@page import="java.util.List"%>
<%@page import="br.com.siscultbook.bean.Editora"%>
<%----%> 
<link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/screen.css" />
 <script type="text/javascript">
	$(document).ready(function(){
		$("#anoPublicacao").mask("9999");
	});
</script>
<style>

#cadastros input.error {
	padding:0.15em;
	width:300px;
	border:1px solid #F00;
	background-color:#FFD2D2;
	font:bold 0.95em arial, sans-serif;
	-moz-border-radius:0.4em;
	-khtml-border-radius:0.4em;
}
#cadastros .radio label, #cadastros .radio input {
	vertical-align:middle;
	display:inline;
	float:none;
	width:auto;
	background:none;
	border:none;
}

</style>
<div id="cadastros">
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
        <!-- our error container -->
    <div class="erros">
        <h4>Acertes o erros para prosseguir:</h4>
        <ul class="disc">
            <li><label for="isbn" class="error">Informe ISBN do Livro, m&iacute;nimo 3 letras!</label></li>
            <li><label for="titulo" class="error">Informe titulo do Livro, m&iacute;nimo 3 letras!</label></li>
            <li><label for="assunto" class="error">Selecione o assunto!</label></li>            
            <li><label for="estoque" class="error">Informe o estoque!</label></li>
            <li><label for="arquivo" class="error">selecione uma figura v&aacute;lida!</label></li>            
            <li><label for="anoPublicacao" class="error">Informe o ano publicação!</label></li>
            <li><label for="listaAutores" class="error">Selecione ao menos um autor!</label></li>            
            <li><label for="descricaoCurta" class="error">Informe breve descrição!</label></li>
            <li><label for="descricaoLonga" class="error">Informe um resumo do livro!</label></li>
            <li><label for="preco" class="error">Informe o preço!</label></li>
            <li><label for="statusLivro" class="error">Selecione o status!</label></li>
            <li><label for="editoraCodigo" class="error">Informe a editora!</label></li>
        </ul>
    </div>
    <%--
        esse form a action dele vai enviar para o SisCultbookController, ele vai receber e redirecionar para
        SisCultbookHelper e o helper vai capturar o valor do paramentro "cmd" input hidden
        de acordo com o parametro ele ira tratar essa requisição e devolver para o SisCultbookController
        com uma página de destino
    --%>
    <form name="form" id="form" action="SisCultbookController?cmd=cadastrarLivro" method="post" enctype="multipart/form-data" >
        <input type='hidden' name='cmd' value="cadastrarLivro" />
        <fieldset class="livro">
  Os campos com (<font color="#ff0000">*</font>) s&atilde;o de preenchimento obrigat&oacute;rio:<br /><br />
            <legend>Informa&ccedil;&otilde;es do Livro</legend>
            <span style="font-size:11px; margin-left:150px;">Obs. s&oacute; n&uacute;meros</span>
<div>
                <label for="isbn"><font color="#ff0000">*</font> ISBN do Livro</label> <input id="isbn" name="isbn" value="${produto.isbn}" type="text" class="{validate:{required:true,minlength:3,number:true}}">
            </div>
            <div>
                <label for="titulo"><font color="#ff0000">*</font>  Titulo do Livro</label> <input id="titulo" name="titulo" value="${produto.titulo}" type="text" class="{validate:{required:true,minlength:3}}">
            </div>
            <div>
                <label for="assunto"><font color="#ff0000">*</font>  Assunto</label>
                <select name="assunto" id="assunto" class="fabrikinput inputbox {validate:{required:true}}">
                    <option value="" selected="selected">Escolha o Assunto</option>
                    <% for (Assunto assunto : Assunto.values()) {%>
                    <% if (assunto.equals(request.getAttribute("livroAssunto"))) {%>
                    <option value="<%=assunto.name()%>" selected="selected" ><%=assunto.name()%></option>
                    <% } else {%>
                    <option value="<%=assunto.name()%>"><%=assunto.name()%></option>
                    <% }%>
                    <% }%>
                </select>
            </div>
            <span style="font-size:11px; margin-left:150px;">Obs. s&oacute; n&uacute;meros</span>
<div>
                <label for="estoque"><font color="#ff0000">*</font>  Qtd Estoque</label> <input id="estoque" name="estoque" value="${produto.estoque}" type="text" class="{validate:{required:true,number:true}}">
            </div>
            <div style="height:50px;">
                <label for="arquivo" style="height:50px;">
                    <font color="#ff0000">*</font> Selecione Arquivo:
        <span style="font-size:11px;">extens&atilde;o:(.jpg|.png)</span> </label>
                <input type="hidden"  name="MAX_FILE_SIZE" value="10485760" /><!-- 10MB de arquivo 01/04/2010 SG Anders -->
                <input name="arquivo" type="file" class="campoTexto {validate:{required:true,accept:'jpg?|png|gif'}}" id="arquivo" style="width:300px;" tabindex="4" accesskey="4" />
            </div>
            <span style="font-size:11px; margin-left:150px;">Obs. s&oacute; n&uacute;meros</span>
<div>
                <label for="anoPublicacao"><font color="#ff0000">*</font> Ano da Publicação</label> <input id="anoPublicacao" name="anoPublicacao" value="${produto.anoPublicacao}" type="text" class="{validate:{required:true,number:true}}">
            </div>
            <div>
                <label for="listaAutores"><font color="#ff0000">*</font> Autores Cadastrados</label>
                
                
                <table width="410" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="300"><select name="listaAutores" size="4" multiple class="fabrikinput inputbox {validate:{required:true}}">                   
                    <% List<Autor> autores = (List<Autor>) request.getAttribute("autores");
                                List<Integer> valores = (List<Integer>) request.getAttribute("teste");                              
                                ArrayList<Integer> valores1 = new ArrayList<Integer>();
                                if (valores != null) {
                                    Iterator iterator = valores.iterator();
                                    for (int i = 0; i < valores.size(); i++) {
                                        String num = iterator.next().toString();
                                        valores1.add(Integer.parseInt(num));                                        
                                    }
                                    for (int i = 0; i < autores.size(); i++) {
                    %>
                    <option value="<%=autores.get(i).getCodigoAutor()%>"
                            <% if (valores1.contains(autores.get(i).getCodigoAutor())) { out.println("selected");}%> ><%=autores.get(i).getSobrenome()%></option>

                    <% }%>
                    <% } else {
                               for (Autor autor : autores) {%>
                    <option value="<%=autor.getCodigoAutor()%>" ><%=autor.getSobrenome()%></option>
                    <%}%>
                    <% }%>
                </select></td>
                    <td width="110" align="center" valign="middle"><a href="SisCultbookController?cmd=editarAutor" title="Cadastrar Autor"><img src="images/users_add-2.png" title="Cadastrar Autor" alt="Cadastrar Autor" width="32" height="32" border="0" align="middle" /></a>
                  </table>
			</div>
                

            <div>
                <label for="descricaoCurta"><font color="#ff0000">*</font> Descrição Curta</label> <input id="descricaoCurta" name="descricaoCurta" value="${produto.descricaoCurta}" type="text" class="{validate:{required:true}}">
            </div>
            <div>
                <label for="descricaoLonga"><font color="#ff0000">*</font> Descrição Longa</label> 
                <textarea style="height:70px;" name="descricaoLonga" cols="40" id="descricaoLonga" class="{validate:{required:true}}">${produto.descricaoLonga}</textarea>
            </div>  
            <span style="font-size:11px; margin-left:150px;">Obs. s&oacute; n&uacute;mero e v&iacute;rgula</span>          
          <div>
                <label for="preco"><font color="#ff0000">*</font> Preço</label> <input id="preco" name="preco" value="${produto.preco}" type="text" class="{validate:{required:true}}">
            </div>

            <div class="radio">
              <fieldset>
                    <legend><span class="stiloSpan"><font color="#ff0000">*</font> Status do Livro</span></legend>
                    <% for (Status status : Status.values()) {%>
                    <% if (status.equals(request.getAttribute("livroStatus"))) {%>
                    <div class="fabrik_subelement" style="float: left; width: 98%;margin:0.2em 0 0 0;">
                      <input id="statusLivro" name="statusLivro" value="<%=status.name()%>" type="radio" checked class="{validate:{required:true}}">
                      <label for="statusLivro"><%=status.name()%>.</label>
                    </div>
                    <% } else {%>
                    <div class="fabrik_subelement" style="float: left; width: 98%;margin:0.2em 0 0 0;">
                      <input id="statusLivro" name="statusLivro" value="<%=status.name()%>" type="radio" class="{validate:{required:true}}">
                      <label for="statusLivro"><%=status.name()%>.</label>
                    </div>
                    <% }%>
                    <% }%>
                </fieldset>
            </div>

            <div>
                <label for="editoraCodigo"><font color="#ff0000">*</font> Editora Cadastradas</label>
                
                
                <table width="414" border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td width="300">
                    <select name="editoraCodigo" id="editoraCodigo" class="fabrikinput inputbox {validate:{required:true}}">
                    <option value="" selected="selected">Escolha a Editora</option>
                    <% List<Editora> editoras = (List<Editora>) request.getAttribute("editoras");
                                for (Editora editora : editoras) {
                                    if (editora.getCodigoEditora() == request.getAttribute("produtoEditora")) {
                    %>
                    <option value="<%=editora.getCodigoEditora()%>" selected="selected" ><%=editora.getNomeEditora()%></option>
                    <% } else {%>
                    <option value="<%=editora.getCodigoEditora()%>" ><%=editora.getNomeEditora()%></option>
                    <% }%>
                    <% }%>
                </select>
                </td>
                    <td width="114" align="center" valign="middle">&nbsp;<a href="SisCultbookController?cmd=editarEditora" title="Cadastrar Editora"><img src="images/add1.png" alt="Cadastrar Editora" width="32" height="32" border="0" title="Cadastrar Editora" /></a></td>
                  </tr>
                </table>

                
            </div>
                        <div class="radio">
                <fieldset>
                    <legend><span class="stiloSpan">Pagina Inicial</span></legend>
                    <div style="float: left; width: 300px; margin:0.2em 0 0 0;">

                        <input id="paginaInicial" name="paginaInicial"
                               value="sim"
                               type="checkbox"
                               <% if(request.getAttribute("paginaInicial")!= null) out.println("checked"); %>>
                        <label for="paginaInicial">Deseja publicar esse livro na pagina Principal?</label>
                    </div>      
                </fieldset>
            </div>
        </fieldset>

        <div>

            <button type="submit" id="submit-go"><p style="margin-top:-4px;">Salvar</p></button>
        </div>
    </form>
</div>