 
<link href="../cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="../css/screen.css" />
<%----%>
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
            <li><label for="nomeNivelAcesso" class="error">Informe nome, m&iacute;nimo 3 letras!</label></li>
        </ul>
    </div>
    <%--
        esse form a action dele vai enviar para o SisCultbookController, ele vai receber e redirecionar para
        SisCultbookHelper e o helper vai capturar o valor do paramentro "cmd" input hidden
        de acordo com o parametro ele ira tratar essa requisição e devolver para o SisCultbookController
        com uma página de destino
    --%>
    <form name="form" id="form" action="SisCultbookController" method="post" >
        <input type='hidden' name='cmd' value="cadastrarNivelAcesso" />
        <fieldset class="lock2">
        O campo com (<font color="#ff0000">*</font>) &eacute; de preenchimento obrigat&oacute;rio:<br />
        <br />
            <legend>Informa&ccedil;&otilde;es Editora</legend>
            <br />
            <div>
                <label for="nomeNivelAcesso"><span id="obrigatorio">*</span> Nome Nível  Acesso</label> 
                <input id="nomeNivelAcesso" name="nomeNivelAcesso" value="${nivel.nivelDeAcesso.nivelDeAcesso}" type="text" class="{validate:{required:true,minlength:3}}">
            </div>
         </fieldset>
        <div>
            <button type="submit" id="submit-go"><p style="margin-top:-4px;">Salvar</p></button>
        </div>
    </form>
</div>