function valida(form1){
    if (form1.nome.value.length==""){
        window.alert ("O nome deve ser preenchido.");
        //window.confirm("O nome deve ser preenchido.")
        form1.nome.focus();
        return (false);
    }else{
        if (document.form1.email.value=="")
        {
            alert('\nO e-mail deve ser preenchido.');
            document.form1.email.select();
            document.form1.email.focus();
            return false;
        }else {
            if (document.form1.email.value.indexOf('@',0)==-1 ||
                document.form1.email.value.indexOf('.',0)==-1)
                {
                alert('\nEste e-mail não é válido.');
                document.form1.email.select();
                document.form1.email.focus();
                return false
            }else {
                if (document.form1.email.value.length<10 ||
                    document.form1.email.value.indexOf(' ',0)==0)
                    {
                    alert('\nO endereço de e-mail deve ter no mínimo 10 caracteres.');
                    document.form1.email.select();
                    document.form1.email.focus();
                    return false;
                }else{
                    //form1.target = "newsletter"
                    //form1.action = "http://www.mqm.com.br/?cmdPage=newsletter&nome=" + document.form1.nome.value + "&email=" + document.form1.email.value;
					form1.action = "#?cmdPage=newsletter&nome=" + document.form1.nome.value + "&email=" + document.form1.email.value;
                    form1.submit();
                    apaga();
                    return true;
                }
            }
        }
    }
}

function apaga()
{
    document.form1.nome.value = "";
    document.form1.email.value = "";
}