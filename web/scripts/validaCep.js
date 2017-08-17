function valida(shipinfo){
    if (shipinfo.cep.value.length==""){
        window.alert ("Preencha o CEP corretamente.");
        shipinfo.cep.focus();
        return (false);
    } else {
        if (shipinfo.cepc.value.length==""){
            window.alert ("Preencha o CEP corretamente.");
            shipinfo.cepc.focus();
            return (false);
        } else {
					
            var checkOK = "0123456789";
            var checkStr = shipinfo.cep.value;
            var allValid = true;
            var decPoints = 0;
            var allNum = "";
            for (i = 0;  i < checkStr.length;  i++)
            {
                ch = checkStr.charAt(i);
                for (j = 0;  j < checkOK.length;  j++)
                    if (ch == checkOK.charAt(j))
                        break;
                if (j == checkOK.length)
                {
                    allValid = false;
                    break;
                }
                allNum += ch;
            }
            if (!allValid)
            {
                alert("O \"CEP\" Deve manter o formato \"12345-123\".");
                shipinfo.cep.focus();
                return (false);
            }
				  
				  
            var checkOK = "0123456789";
            var checkStr = shipinfo.cepc.value;
            var allValid = true;
            var decPoints = 0;
            var allNum = "";
            for (i = 0;  i < checkStr.length;  i++)
            {
                ch = checkStr.charAt(i);
                for (j = 0;  j < checkOK.length;  j++)
                    if (ch == checkOK.charAt(j))
                        break;
                if (j == checkOK.length)
                {
                    allValid = false;
                    break;
                }
                allNum += ch;
            }
            if (!allValid)
            {
                alert("O \"CEP\" Deve manter o formato \"12345-123\".");
                shipinfo.cepc.focus();
                return (false);
            }

            return (true)
        }
    }
}


function Validator(theForm){
}


var TabArr = new Array();
TabArr["cep"] = [5, "cepc"];
TabArr["cepc"] = [3, "enviar"];
var prevlength = 0;
var prevname = "";
function handKeydown(e)
{
    var campo;
    if(document.all)
        campo = window.event.srcElement;
    else
        campo = e.target;
    if(campo.name){
        prevlength = campo.value.length;
        prevname = campo.name;
    }
}
function handKeyup(e)
{
    var campo;
    if(document.all)
        campo = window.event.srcElement;
    else
        campo = e.target;
    if(campo.name && TabArr[campo.name]){
        if(	(campo.value.length >= TabArr[campo.name][0]) &&
            (campo.value.length > prevlength) &&
            (campo.name == prevname) &&
            campo.form.elements[TabArr[campo.name][1]]){
            campo.form.elements[TabArr[campo.name][1]].focus();
            prevname = campo.form.elements[TabArr[campo.name][1]].name;
        }
    }
}
window.document.onkeyup = handKeyup;
window.document.onkeydown = handKeydown;
if(document.layers)
    window.document.captureEvents(Event.KEYUP | Event.KEYDOWN)
