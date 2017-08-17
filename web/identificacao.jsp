<link href="cultbookFirefox.css" rel="stylesheet" type="text/css" media="all" />
<link rel="stylesheet" type="text/css" href="css/catalogoLivro.css" />
<%----%>        
<div id="catalogoLivro">
    <!-- path -->

    <div id="path">
        <ul>
            <li class="base">Você está aqui:</li>
            <li class="first">${titulo}</li>

        </ul>
    </div>
    <!-- end path -->
    <div id="erro">
        ${mensagem}
    </div>
    <div id="sobre" >

        <span class="shopDesc">Sua primeira compra na CPB?</span>
        <div class="push10"></div>
        <p class="cart">
				Por favor clique abaixo para fazer um cadastro, deste modo, você apenas precisará informar seu nome de usuário 
				e senha para efetuar futuras compras.
        </p>
        <div class="push10"></div>
        <a href="SisCultbookController?cmd=dadosCliente&amp;codigo=${codigoUsuario}" class="remove">» Preencher Cadastro</a>


        <div class="push20"></div>
        <div class="push20"></div>

        <span class="shopDesc">Já comprou conosco? (identifique-se)</span>
        <div class="push10"></div>
        <p class="cart">
            <b>Se você já tem cadastro na CPB,</b> apenas complete o seu nome de usuario e sua senha de acesso e clique no botão "Continuar".
        </p>

        <p class="cart">
            <b>Se você esqueceu sua senha,</b> digite apenas seu nome de usuário
            e clique no link "Enviar por e-Mail", a sua senha será automaticamente
            enviada para o e-mail que consta em seu cadastro.
        </p>
        <div class="push10"></div>
        <div id="erro">${mensagem}</div>
        <form name="check_senha" action="SisCultbookController" onsubmit="return check_login(this);" method="post">
            <input type='hidden' name='cmd' value="identificarUsuario" />
            <label class="labelCadastro">Identifica&ccedil;&atilde;o do Usuário - CPF!</label><br>
            <input class="campo" size="30" maxlength="50" onfocus="if(this.value=='Digite seu CPF')this.value='';" onblur="if(this.value=='')this.value='Digite seu CPF';" value="Digite seu CPF" name="login">

            <div class="push5"></div>

            <label class="labelCadastro">Senha:</label><br>
            <input class="campo" size="19" maxlength="10" name="senha" type="password"> &nbsp;

            <input class="botao" value="Continuar" name="Submit" type="submit">

            <div class="push10"></div>
            <a href="#" class="remove">» Esqueci Minha Senha. Enviar por e-Mail</a>

        </form>


        <script language="javascript">
            <!--//
            function check_envio(check_senha){
                if (check_senha.nome.value == "Digite sua identificação") {
                    window.alert ("Preencha seu CPF.");check_senha.nome.focus();return (false);
                } else {
                    if (check_senha.nome.value.length == "") {
                        window.alert ("Preencha seu CPF.");check_senha.nome.focus();return (false);
                    } else {
                        document.check_senha.submit();
                        return (true)}}
            }
					
            function check_login(check_senha){
                if (check_senha.nome.value == "Digite sua identificação") {
                    window.alert("Preencha seu CPF."); check_senha.nome.focus(); return (false);
                } else {
                    if (check_senha.nome.value.length == "") {
                        window.alert ("Preencha seu CPF.");check_senha.nome.focus();return (false);
                    } else {
                        if (check_senha.senha.value.length == "") {
                            window.alert ("Preencha sua senha.");check_senha.senha.focus();return (false);
                        } else {
                            return (true)}}}
            }
            //-->
        </script>

    </div>
</div>