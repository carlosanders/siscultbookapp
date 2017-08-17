<%@page import="br.com.siscultbook.bean.Status"%>
<%@page import="br.com.siscultbook.bean.Sexo"%>
<%@page import="br.com.siscultbook.bean.Estados"%>

<%----%>  
<link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/screen.css" />
    <script type="text/javascript">
	$(document).ready(function(){
		$("#cpf").mask("999.999.999-99");
		$("#cep").mask("99999-999");
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
         <div class="erros">
        <h4>Acertes o erros para prosseguir:</h4>
        <ul class="disc">
            <li><label for="nomeCompleto" class="error">Informe nome, m&iacute;nimo 3 letras!</label></li>
            <li><label for="dataNascimento" class="error">Informe uma data v&aacute;lida!</label></li>
            <li><label for="cpf" class="error">Informe um CPF v&aacute;lido!</label></li>
            <li><label for="sexo" class="error">Selecione o sexo!</label></li>
            <li><label for="rua" class="error">Informe a rua, m&iacute;nimo 3 caracteres!</label></li>
            <li><label for="bairro" class="error">Informe o bairro, m&iacute;nimo 3 caracteres!</label></li>
            <li><label for="cidade" class="error">Informe a cidade, m&iacute;nimo 3 caracteres!</label></li>
            <li><label for="cep" class="error">Informe o cep!</label></li>
            <li><label for="estado" class="error">Informe o estado!</label></li>            
            <li><label for="autorSobrenome" class="error">Informe o sobrenome!</label></li>
            <li><label for="statusAutor" class="error">Selecione o status!</label></li>
        </ul>
    </div>
    <%--
        esse form a action dele vai enviar para o SisCultbookController, ele vai receber e redirecionar para
        SisCultbookHelper e o helper vai capturar o valor do paramentro "cmd" input hidden
        de acordo com o parametro ele ira tratar essa requisição e devolver para o SisCultbookController
        com uma página de destino
    --%>
    <form name="form" id="form" action="SisCultbookController" method="post" >
        <input type='hidden' name='cmd' value="atualizarAutor" />
        <input type='hidden' name='codigo' value="${param.codigo}" />
        <fieldset class="accept_page">
        Os campos com (<font color="#ff0000">*</font>) s&atilde;o de preenchimento obrigat&oacute;rio:<br /><br />
            <legend>Informa&ccedil;&otilde;es do Autor</legend>
            <div>
              <label for="nomeCompleto"><font color="#FF0000">*</font> Nome Completo</label> 
              <input id="nomeCompleto" name="nomeCompleto" value="${autor.nomeCompleto}" type="text" class="{validate:{required:true,minlength:3}}" tabindex="1">
            </div>
            <div>
                <label for="dataNascimento"><font color="#FF0000">*</font> Data Nascimento</label> 
                <input id="dataNascimento" name="dataNascimento" value="${autorDataNascimento}" type="text"  class="{validate:{required:true,dateBR:true}}" tabindex="2">
            </div>
            <span style="font-size:11px; margin-left:150px;">Obs. s&oacute; n&uacute;meros</span>
            <div>
                <label for="cpf"><font color="#FF0000">*</font> CPF</label> 
                <input id="cpf" name="cpf" value="${autor.cpf}" type="text" class="{validate:{required:true,verificaCPF:true}}" tabindex="3">
            </div>
            <div class="radio">
                <fieldset>

                    <legend><span><font color="#FF0000">*</font> Sexo</span></legend>
                    <% for (Sexo sexo : Sexo.values()) {%>
                    <% if (sexo.equals(request.getAttribute("autorSexo"))) {%>
                    <div class="fabrik_subelement" style="float: left; width: 98%; margin:0.2em 0 0 0;">
                        <input id="<%=sexo.name()%>" name="sexo" value="<%=sexo.name()%>" type="radio" checked class="{validate:{required:true}}" tabindex="4">
                        <label for="<%=sexo.name()%>"><%=sexo.name()%>.</label>
                    </div>
                    <% } else {%>
                    <div class="fabrik_subelement" style="float: left; width: 98%; margin:0.2em 0 0 0;">
                        <input id="<%=sexo.name()%>" name="sexo" value="<%=sexo.name()%>" type="radio" class="{validate:{required:true}}" tabindex="5">
                        <label for="<%=sexo.name()%>"><%=sexo.name()%>.</label>
                    </div>
                    <% }%>
                    <% }%>

                </fieldset>
            </div>

            <div>
                <label for="cidadeContato"><font color="#FF0000">*</font> Rua</label> 
                <input id="rua" name="rua" value="${autor.endereco.rua}" type="text" class="{validate:{required:true,minlength:3}}" tabindex="6">
            </div>
            <div>
                <label for="cidadeContato"><font color="#FF0000">*</font> Bairro</label> 
                <input id="bairro" name="bairro" value="${autor.endereco.bairro}" type="text" class="{validate:{required:true,minlength:3}}" tabindex="7">
            </div>
            <div>
                <label for="cidadeContato"><font color="#FF0000">*</font> Cidade</label>
                <input id="cidade" name="cidade" value="${autor.endereco.cidade}" type="text" class="{validate:{required:true,minlength:3}}" tabindex="8">
            </div>
            <span style="font-size:11px; margin-left:150px;">Obs. s&oacute; n&uacute;meros</span>
            <div>
                <label for="cepContato"><font color="#FF0000">*</font> CEP</label> 
                <input id="cep" name="cep" value="${autor.endereco.cep}" type="text" class="{validate:{required:true}}" tabindex="9">
            </div>

            <div>
                <label for="estado"><font color="#FF0000">*</font> Estado</label>
                <select name="estado" id="estado" class="fabrikinput inputbox {validate:{required:true}}" tabindex="10">
                    <option value="" selected="selected">Escolha o estado</option>
                    <% for (Estados estado : Estados.values()) {%>
                    <% if (estado.equals(request.getAttribute("autorEstado"))) {%>
                    <option value="<%=estado.name()%>" selected="selected" ><%=estado.name()%></option>
                    <% } else {%>
                    <option value="<%=estado.name()%>"><%=estado.name()%></option>
                    <% }%>
                    <% }%>
                </select>
            </div>
            <div>
                <label for="autorSobrenome"><span><font color="#FF0000">*</font> Sobrenome</span></label> 
                <input id="autorSobrenome" name="autorSobrenome" value="${autor.sobrenome}" type="text" class="{validate:{required:true}}" tabindex="11">
            </div>
            <div class="radio">
              <fieldset>
                    <legend><span class="stiloSpan"><font color="#FF0000">*</font> Status do Autor</span></legend>
                    <% for (Status status : Status.values()) {%>
                    <% if (status.equals(request.getAttribute("autorStatus"))) {%>
                    <div class="fabrik_subelement" style="float: left; width: 98%;margin:0.2em 0 0 0;">
                        <input id="statusAutor" name="statusAutor" value="<%=status.name()%>" type="radio" checked class="{validate:{required:true}}" tabindex="12">
                        <label for="statusAutor"><%=status.name()%>.</label>
                    </div>
                    <% } else {%>
                    <div class="fabrik_subelement" style="float: left; width: 98%;margin:0.2em 0 0 0;">
                        <input id="<%=status.name()%>" name="statusAutor" value="<%=status.name()%>" type="radio" class="{validate:{required:true}}" tabindex="13">
                        <label for="<%=status.name()%>"><%=status.name()%>.</label>
                    </div>
                    <% }%>
                    <% }%>
                </fieldset>
            </div>
        </fieldset>

        <div>

            <button type="submit" id="submit-go" tabindex="14"><p style="margin-top:-4px;">Atualizar</p></button>
        </div>
    </form>
</div>