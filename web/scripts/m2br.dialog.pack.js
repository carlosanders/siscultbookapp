var m2brTimer="";
$.fn.m2brDialog=function(a){
    var b={
        largura:300,
        altura:120,
        tipo:"alerta",
        titulo:"",
        texto:"Alerta!",
        draggable:false,
        botoes:{
            1:{
                label:"OK",
                tipo:"fechar"
            }
        },
    tempoExibicao:0,
    condicao:{
        origem:function(){
            return false
            },
        retorno:function(){
            return false
            }
        }
};

var a=$.extend(b,a);
$(this).click(function(){
    c();
    if(a.condicao.origem()==true){
        a.condicao.retorno();
        return true
        }
        clearTimeout(m2brTimer);
    $("select").each(function(){
        $(this).hide()
        });
    $(document.body).prepend('<div id="m2brOverlayFixed"><div id="m2brOverlay"></div></div>');
    $(document.body).prepend('<div id="m2brDialogJanelaFixed"><div id="m2brDialogJanela"></div></div>');
    $("#m2brDialogJanela").append('<h2><a href="#" id="m2brDialogFechar"></a>'+a.titulo+"</h2>").append('<div id="m2brDialogJanela-texto">'+a.texto+"</div>").addClass("m2brDialog-"+a.tipo);
    $("#m2brDialogFechar").click(c);
    if(a.draggable==true&&jQuery.isFunction($.fn.draggable)){
        $("#m2brDialogJanela").draggable({
            handle:"h2"
        });
        $("#m2brDialogJanela h2").css("cursor","move")
        }
        $("#m2brOverlay").fadeIn(150,function(){
        if($.browser.msie==true&&$.browser.version<8){
            $("#m2brOverlay").css("opacity",0.65)
            }
            $("#m2brDialogJanela").fadeIn(200,function(){
            $("#m2brOverlay").click(c)
            })
        });
    $("#m2brDialogJanela").css({
        height:a.altura+"px",
        width:a.largura+"px",
        marginLeft:"-"+(a.largura/2)+"px",
        marginTop:"-"+(a.altura/2)+"px"
        });
    if($.browser.msie==true&&$.browser.version<7){
        $("#m2brOverlay").css("top",$(window).scrollTop());
        $("#m2brDialogJanela").css("top",$(window).scrollTop()+($(window).height()/2)-(a.altura/2))
        }
        $("#m2brDialogJanela").append('<div id="m2brDialogJanela-botoes"></div>');
    for(x in a.botoes){
        $("#m2brDialogJanela-botoes").append('<a href="#" id="m2brDialog-botao-'+x+'" class="m2brDialogBotao">'+a.botoes[x].label+"</a>");
        if(a.botoes[x].tipo=="fechar"){
            $("#m2brDialog-botao-"+x).click(c)
            }else{
            if(a.botoes[x].tipo=="link"){
                $("#m2brDialog-botao-"+x).attr("href",a.botoes[x].endereco)
                }else{
                if(a.botoes[x].tipo=="script"){
                    $("#m2brDialog-botao-"+x).click(a.botoes[x].funcao);
                    $("#m2brDialog-botao-"+x).click(c)
                    }
                }
        }
    }
    $("#m2brDialogJanela-botoes a").click(function(){
    $("#m2brDialogFechar").hide();
    $("#m2brOverlay").unbind("click");
    $("#m2brDialogJanela-botoes").html('<div class="carregando"></a>')
    });
if(!isNaN(a.tempoExibicao)&&a.tempoExibicao>0){
    m2brTimer=setTimeout(c,a.tempoExibicao*1000)
    }
    return false
});
var c=function(){
    $("#m2brOverlay").unbind("click");
    clearTimeout(m2brTimer);
    $("#m2brOverlayFixed").remove();
    $("#m2brDialogJanelaFixed").remove();
    $("select").each(function(){
        $(this).show()
        });
    return false
    }
};