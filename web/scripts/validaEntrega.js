function vali(entrega){
    //validar nome
    if (entrega.nome_ent.value.length==""){
        window.alert ("O campo Nome para ENTREGA deve ser preenchido.");
        entrega.nome_ent.focus();
        return (false);
    } else {
        //validar endereco rua
        if (entrega.endereco_ent.value.length==""){
            window.alert ("O campo ENDERECO para ENTREGA deve ser preenchido.");
            entrega.endereco_ent.focus();
            return (false);
        } else {
			
            //validar bairro
            if (entrega.bairro_ent.value.length==""){
                window.alert ("O campo Bairro para ENTREGA deve ser preenchido.");
                entrega.bairro_ent.focus();
                return (false);
            } else {
                //validar cidade
                if (entrega.cidade_ent.value.length==""){
                    window.alert ("O campo CIDADE para ENTREGA deve ser preenchido.");
                    entrega.cidade_ent.focus();
                    return (false);
                } else {
                    //validar estado
                    if (entrega.uf_ent.value.length==""){
                        window.alert ("O campo ESTADO para ENTREGA deve ser preenchido.");
                        entrega.uf_ent.focus();
                        return (false);
						
                    } else {
						
                        //validar CEP
                        if (entrega.cep_ent.value.length==""){
                            window.alert ("O campo CEP para ENTREGA deve ser preenchido.");
                            entrega.cep_ent.focus();
                            return (false);
                        } else {
	
                            var checkOK = "0123456789-";
                            var checkStr = entrega.cep_ent.value;
                            var allValid = true;
                            var decPoints = 0;
                            var allNum = "";
                            for (i = 0;  i < checkStr.length;  i++){
                                ch = checkStr.charAt(i);
                                for (j = 0;  j < checkOK.length;  j++)if (ch == checkOK.charAt(j))break;if (j == checkOK.length){
                                    allValid = false;
                                    break;
                                }
                                allNum += ch;
                            }
                            if (!allValid){
                                alert("Insira apenas numeros no campo \"CEP\".");
                                entrega.cep_ent.focus();
                                return (false);
                            }
							
                            //validar telefone
                            if (entrega.telefone_ent.value.length==""){
                                window.alert ("O campo TELEFONE para ENTREGA deve ser preenchido.");
                                entrega.telefone_ent.focus();
                                return (false);
                            } else {	
                                var checkOK = "0123456789-() ";
                                var checkStr = entrega.telefone_ent.value;
                                var allValid = true;
                                var decPoints = 0;
                                var allNum = "";
                                for (i = 0;  i < checkStr.length;  i++){
                                    ch = checkStr.charAt(i);
                                    for (j = 0;  j < checkOK.length;  j++)if (ch == checkOK.charAt(j))break;if (j == checkOK.length){
                                        allValid = false;
                                        break;
                                    }
                                    allNum += ch;
                                }
                                if (!allValid){
                                    alert("Insira apenas numeros no campo \"TELEFONE\".");
                                    entrega.telefone_ent.focus();
                                    return (false);
                                }
                                //validar frete
                                var frm=document.entrega;
                                var tipo = 0;
                                var frete = "";
                                for (var i = 0; i < frm.ffrete.length; i++) {
                                    if (frm.ffrete[i].checked) {
                                        frete = frm.ffrete[i].value;
                                    }
                                }
		
                                if (frete==""){
                                    alert('Selecione o frete para calcular o total do pedido.');
                                    return (false);
                                }
								
                                //validar forma de pagamento
                                var frm=document.entrega;
                                var tipo = 0;
                                var pagamento = "";
                                for (var i = 0; i < frm.fpagamento.length; i++) {
                                    if (frm.fpagamento[i].checked) {
                                        pagamento = frm.fpagamento[i].value;
                                    }
                                }
		
                                if (pagamento==""){
                                    alert('Selecione uma forma de pagamento.');
                                    return (false);
                                }
		
                                if (pagamento == "cartao"){
		
                                    //Cartão
                                    if (entrega.tipo_cartao.value.length==""){
                                        window.alert ("Escolha o Tipo do cartao..");
                                        entrega.tipo_cartao.focus();
                                        return (false);
                                    }
                                    if (entrega.parcelas.value.length==""){
                                        window.alert ("Selecione a quantidade de parcelas.");
                                        entrega.parcelas.focus();
                                        return (false);
                                    }
                                    if (entrega.nome_cartao.value.length==""){
                                        window.alert ("Defina o Titular do cartao.");
                                        entrega.nome_cartao.focus();
                                        return (false);
                                    }
                                    if (entrega.num_cartao.value.length==""){
                                        window.alert ("O campo \"Numero do cartao\" está em branco.");
                                        entrega.num_cartao.focus();
                                        return (false);
                                    }

                                    var checkOK = "0123456789";
                                    var checkStr = entrega.num_cartao.value;
                                    var allValid = true;
                                    var decPoints = 0;
                                    var allNum = "";
                                    for (i = 0;  i < checkStr.length;  i++){
                                        ch = checkStr.charAt(i);
                                        for (j = 0;  j < checkOK.length;  j++)if (ch == checkOK.charAt(j))break;if (j == checkOK.length){
                                            allValid = false;
                                            break;
                                        }
                                        allNum += ch;
                                    }
                                    if (!allValid){
                                        alert("Insira apenas numeros no campo \"Numero do cartao\".");
                                        entrega.num_cartao.focus();
                                        return (false);
                                    }
									
                                    //Desativado para fazer testes, mas essa funcao valida os cartoes se estao corretos ou não
                                    //else{
                                    //valida os cartoes se estao corretos
                                    //if (!validateCard(entrega.num_cartao.value,entrega.tipo_cartao.value,entrega.mes_validade.value,entrega.ano_validade.value))
                                    //{
                                    //  entrega.num_cartao.focus();
                                    //return (false);
                                    //}
										
                                    //}
		
                                    if (entrega.cod_verificador.value.length=="" || entrega.cod_verificador.value.length != 3 ){
                                        window.alert ("Digite o Codigo verificador de seu cartao.");
                                        entrega.cod_verificador.focus();
                                        return (false);
                                    }
                                    var checkOK = "0123456789";
                                    var checkStr = entrega.cod_verificador.value;
                                    var allValid = true;
                                    var decPoints = 0;
                                    var allNum = "";
                                    for (i = 0;  i < checkStr.length;  i++){
                                        ch = checkStr.charAt(i);
                                        for (j = 0;  j < checkOK.length;  j++)if (ch == checkOK.charAt(j))break;if (j == checkOK.length){
                                            allValid = false;
                                            break;
                                        }
                                        allNum += ch;
                                    }
                                    if (!allValid){
                                        alert("Insira apenas numeros no campo \"Codigo verificador\".");
                                        entrega.cod_verificador.focus();
                                        return (false);
                                    }
	
                                    if (entrega.mes_validade.value.length==""){
                                        window.alert ("Escolha o mes de validade.");
                                        entrega.mes_validade.focus();
                                        return (false);
                                    }
                                    if (entrega.ano_validade.value.length==""){
                                        window.alert ("Escolha o ano de validade.");
                                        entrega.ano_validade.focus();
                                        return (false);
                                    }
		
                                }
			
                                return (true)
                            }
                        }
                    }
                }
            }
        }
    }
}

function mod10( cardNumber ) { // LUHN Formula for validation of credit card numbers.
    var ar = new Array( cardNumber.length );
    var i = 0,sum = 0;


    for( i = 0; i < cardNumber.length; ++i ) {
        ar[i] = parseInt(cardNumber.charAt(i));
    }
    for( i = ar.length -2; i >= 0; i-=2 ) { // you have to start from the right, and work back.
        ar[i] *= 2;							 // every second digit starting with the right most (check digit)
        if( ar[i] > 9 ) ar[i]-=9;			 // will be doubled, and summed with the skipped digits.
    }										 // if the double digit is > 9, ADD those individual digits together


    for( i = 0; i < ar.length; ++i ) {
        sum += ar[i];						 // if the sum is divisible by 10 mod10 succeeds
    }
    return (((sum%10)==0)?true:false);
}


function expired( month, year ) {
    var now = new Date();							// this function is designed to be Y2K compliant.
    var expiresIn = new Date(year,month,0,0,0);		// create an expired on date object with valid thru expiration date
    expiresIn.setMonth(expiresIn.getMonth()+1);		// adjust the month, to first day, hour, minute & second of expired month
    if( now.getTime() < expiresIn.getTime() ) return false;
    return true;									// then we get the miliseconds, and do a long integer comparison
}


function validateCard(cardNumber,cardType,cardMonth,cardYear) {
    if( cardNumber.length == 0 ) {						//most of these checks are self explanitory
        alert("Please enter a valid card number.");
        form1.numero_cartao.focus();
        return false;
    }
    for( var i = 0; i < cardNumber.length; ++i ) {		// make sure the number is all digits.. (by design)
        var c = cardNumber.charAt(i);


        if( c < '0' || c > '9' ) {
            alert("Erro no número do cartão. Não use espaços ou hifens.");
            return false;
        }
    }
    var length = cardNumber.length;			//perform card specific length and prefix tests


    switch( cardType ) {
        case '5':


            if( length != 15 ) {
                alert("Erro na validação :: American Express.");
                return;
            }
            var prefix = parseInt( cardNumber.substring(0,2));


            if( prefix != 34 && prefix != 37 ) {
                alert("Erro na validação :: American Express.");
                return;
            }
            break;
        case '3':


            if( length != 16 ) {
                alert("Erro na validação :: Dinners.");
                return;
            }
            var prefix = parseInt( cardNumber.substring(0,2));


            if( prefix < 51 || prefix > 55) {
                alert("Erro na validação :: Dinners.");
                return;
            }
            break;
        case '2':


            if( length != 16 ) {
                alert("Erro na validação :: MasterCard.");
                return;
            }
            var prefix = parseInt( cardNumber.substring(0,2));


            if( prefix < 51 || prefix > 55) {
                alert("Erro na validação :: MasterCard.");
                return;
            }
            break;
        case '1':


            if( length != 16 && length != 13 ) {
                alert("Erro na validação :: Visa.");
                return;
            }
            var prefix = parseInt( cardNumber.substring(0,1));


            if( prefix != 4 ) {
                alert("Erro na validação :: Visa.");
                return;
            }
            break;
    }
    if( !mod10( cardNumber ) ) { 		// run the check digit algorithm
        alert("Desculpe! este não é um número de Cartão válido.");
        return false;
    }
    if( expired( cardMonth, cardYear ) ) {							// check if entered date is already expired.
        alert("Desculpe! A data de expiração que você selecionou, faz com que seu cartão seja invalido.");
        return false;
    }
                                                	
    return true; // at this point card has not been proven to be invalid
}
                                            
function CopiaDados()
{


    if (document.entrega.mesmo.checked)
    {
        document.entrega.nome_ent.value = document.entrega.nome.value;
        document.entrega.endereco_ent.value = document.entrega.endereco.value;
        //document.entrega.numero_ent.value = document.entrega.numero.value;
        document.entrega.bairro_ent.value = document.entrega.bairro.value;
        document.entrega.cidade_ent.value = document.entrega.cidade.value;
        //document.entrega.uf_ent.value = document.entrega.uf.value;
        document.entrega.cep_ent.value = document.entrega.cep.value;
        document.entrega.telefone_ent.value = document.entrega.telefone.value;
    //document.entrega.nro_cxp_ent.value = document.entrega.nro_cxp.value;

    }
    else
    {
        document.entrega.nome_ent.value = "";
        document.entrega.endereco_ent.value = "";
        //document.entrega.numero_ent.value = "";
        document.entrega.bairro_ent.value = "";
        document.entrega.cidade_ent.value = "";
        document.entrega.cep_ent.value = "";
        document.entrega.telefone_ent.value = "";
    //document.entrega.nro_cxp_ent.value = "";
    }
	
	
}

function Verificar_frete()
{
    var frm=self.document.entrega;
    var ffrete = 0;
    for (var i = 0; i < frm.ffrete.length; i++) {
        if (frm.ffrete[i].checked) {
            ffrete = frm.ffrete[i].value;
        }
    }
    var select = self.document.entrega.idfrete
    if (select.options[select.selectedIndex].value != "")
    {
        self.location = ('#' + entrega.nome_ent.value + '&aux_end_e1=' + entrega.aux_end_e1_ent.value + '&aux_end_e2=' + entrega.aux_end_e2_ent.value + '&aux_end_e3=' + entrega.aux_end_e3_ent.value + '&aux_end_e4=' + entrega.aux_end_e4_ent.value + '&aux_end_e5=' + entrega.aux_end_e5_ent.value + '&ffrete=' + ffrete + '&idfrete='  + select.options[select.selectedIndex].value)
    }
}

function validar_cep(entrega){
    if (entrega.cep_ent.value.length==""){
        window.alert ("O campo CEP para ENTREGA deve ser preenchido, para que o sistema calcule seu frete.");
        entrega.cep_ent.focus();
        return (false);
    } else {
        self.location = ('#' + entrega.nome_ent.value + '&endereco=' + entrega.endereco_ent.value + '&numero_ent=' + entrega.numero_ent.value + '&bairro=' + entrega.bairro_ent.value + '&uf=' + entrega.uf_ent.value + '&cidade=' + entrega.cidade_ent.value + '&cep_ent=' + entrega.cep_ent.value + '&fone=' + entrega.fone_ent.value + '&nro_cxp_ent=' + entrega.nro_cxp.value + '&ffrete=s');
        return (true);
    }
}
function validar_reload(entrega){
    self.location = ('#' + entrega.nome_ent.value + '&endereco=' + entrega.endereco_ent.value + '&numero_ent=' + entrega.numero_ent.value + '&bairro=' + entrega.bairro_ent.value + '&uf=' + entrega.uf_ent.value + '&cidade=' + entrega.cidade_ent.value + '&cep_ent=' + entrega.cep_ent.value + '&fone=' + entrega.fone_ent.value + '&nro_cxp_ent=' + entrega.nro_cxp.value + '&ffrete=n');
    return (true);
}
