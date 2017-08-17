<%-- --%>
<%@page import="br.com.siscultbook.bean.Status"%>
<link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/screen.css" />
<script type="text/javascript">
	$(document).ready(function(){
		$("#cnpj").mask("99.999.999/9999-99");
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
            <li><label for="nomeEditora" class="error">Informe Editora, m&iacute;nimo 3 letras!</label></li>
            <li><label for="cnpj" class="error">Informe CNPJ!</label></li>
            <li><label for="statusEditora" class="error">Selecione status da Editora!</label></li>
        </ul>
    </div>
    <%--
        esse form a action dele vai enviar para o SisCultbookController, ele vai receber e redirecionar para
        SisCultbookHelper e o helper vai capturar o valor do paramentro "cmd" input hidden
        de acordo com o parametro ele ira tratar essa requisição e devolver para o SisCultbookController
        com uma página de destino
    --%>
    <form name="form" id="form" action="SisCultbookController" method="post" >
        <input type='hidden' name='cmd' value="atualizarEditora" />
        <input type='hidden' name='codigo' value="${param.codigo}" />
        <fieldset class="EditarEditora">
        Os campos com (<font color="#ff0000">*</font>) s&atilde;o de preenchimento obrigat&oacute;rio:<br /><br />
            <legend>Informa&ccedil;&otilde;es Editora</legend>
            <div>
              <label for="nomeEditora"><font color="#FF0000">*</font> Nome Editora</label> <input id="nomeEditora" name="nomeEditora" value="${editora.nomeEditora}" type="text"class="{validate:{required:true}}" >
            </div>
            <span style="font-size:11px; margin-left:150px;">Obs. s&oacute; n&uacute;meros</span>
            <div>
                <label for="cnpj"><font color="#FF0000">*</font> CNPJ</label> <input id="cnpj" name="cnpj" value="${editora.cnpj}" type="text" class="{validate:{required:true,verificaCNPJ:true}}">
            </div>
            <div>
                <label for="obs">Observa&ccedil;&otilde;es:</label>
                <textarea name="obs" rows="6" cols="35">${editora.obs}</textarea>
            </div>
            <div class="radio">
              <fieldset>
                    <legend><span class="stiloSpan"><font color="#FF0000">*</font> Status da Editora</span></legend>
                    <% for (Status status : Status.values()) {%>
                    <% if (status.equals(request.getAttribute("editoraStatus"))) {%>
                    <div class="fabrik_subelement" style="float: left; width: 98%;margin:0.2em 0 0 0;">
                        <input id="statusEditora" name="statusEditora" value="<%=status.name()%>" type="radio" checked class="{validate:{required:true}}">
                        <label for="statusEditora"><%=status.name()%>.</label>
                    </div>
                    <% } else {%>
                    <div class="fabrik_subelement" style="float: left; width: 98%;margin:0.2em 0 0 0;">
                        <input id="statusEditora" name="statusEditora" value="<%=status.name()%>" type="radio" class="{validate:{required:true}}">
                        <label for="statusEditora"><%=status.name()%>.</label>
                    </div>
                    <% }%>
                    <% }%>
                </fieldset>
            </div>
        </fieldset>
        <div>
            <button type="submit" id="submit-go"><p style="margin-top:-4px;">Atualizar</p></button>
        </div>
    </form>
</div>