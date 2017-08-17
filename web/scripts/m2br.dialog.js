/*
 * M2brDialog 
 * - Substitui alertas e e janelas do sistema operacional
 * plugin jQuery desenvolvido por Davi Ferreira (contato@daviferreira.com)
 * collab Rômulo Alves (romulo@logmania.net)
 * @version 2.1 2008-12-08
 *
 * Copyright (c) M2BRNET (http://www.m2brnet.com)
 * Dual licensed under the MIT (MIT-LICENSE.txt)
 * and GPL (GPL-LICENSE.txt) licenses.
 */

// variável para armazenar setTimeOut
var m2brTimer = '';

$.fn.m2brDialog = function(opcoes) {
	
	// opções padrão
	var defaults = {
		largura			: 300,
		altura			: 120,
		tipo			: 'alerta',
		titulo			: '',
		texto			: 'Alerta!',
		draggable		: false,
		botoes			: {
			1: {
				label: 'OK',
				tipo: 'fechar'
			}
		},
		tempoExibicao	: 0,
		condicao		: {
			origem: function() { return false; },
			retorno: function() { return false; }
		}
	};
	var opcoes = $.extend(defaults, opcoes);

	// seta onclick do elemento para exibir janela
	$(this).click(function() {
						   
		// elimina outra instância de alerta
		fechaJanela();

		// verifica se tem condicao
		if (opcoes.condicao.origem() == true) {
			opcoes.condicao.retorno();
			return true;
		}
						   
		// limpa qualquer timeout para evitar conflitos
		clearTimeout(m2brTimer);
		
		// oculta elementos select para evitar bug de sobreposição				   
		$('select').each(function(){ $(this).hide(); });
		
		// cria overlay
		$(document.body).prepend('<div id="m2brOverlayFixed"><div id="m2brOverlay"></div></div>');
		
		// cria divs para janela e configura classe CSS
		$(document.body).prepend('<div id="m2brDialogJanelaFixed"><div id="m2brDialogJanela"></div></div>');
		$('#m2brDialogJanela')
			.append('<h2><a href="#" id="m2brDialogFechar"></a>'+opcoes.titulo+'</h2>')
			.append('<div id="m2brDialogJanela-texto">'+opcoes.texto+'</div>')
			.addClass('m2brDialog-'+opcoes.tipo);
		
		// configura clique do botão fechar
		$('#m2brDialogFechar').click(fechaJanela);
		
		// drag and drop da janela, caso configurada
		if (opcoes.draggable == true && jQuery.isFunction($.fn.draggable)) {
			$('#m2brDialogJanela').draggable( { handle: 'h2' } );
			$('#m2brDialogJanela h2').css('cursor', 'move');
		}
		
		// animação para exibir overlay e janela
		$('#m2brOverlay').fadeIn(150, function() {
			// hack de opacity para IE			
			if ($.browser.msie == true && $.browser.version < 8) {
				$('#m2brOverlay').css('opacity', 0.65);
			}
			// exibe janela
			$('#m2brDialogJanela').fadeIn(200, function() {
				// configura onclick do overlay para fechar a janela
				$('#m2brOverlay').click(fechaJanela);
			});	
		});		
		
		// configura tamanho e margem do dialog
		$('#m2brDialogJanela').css({
			height: opcoes.altura+'px',
			width: opcoes.largura+'px',
			marginLeft: '-'+(opcoes.largura/2)+'px',
			marginTop: '-'+(opcoes.altura/2)+'px'
		});
		
		// hack para IE6 já que não aceita fixed
		if ($.browser.msie == true && $.browser.version < 7) {
			$('#m2brOverlay').css('top', $(window).scrollTop());
			$('#m2brDialogJanela').css('top', $(window).scrollTop() + ($(window).height()/2) - (opcoes.altura/2));
		}
		
		// cria botões
		$('#m2brDialogJanela').append('<div id="m2brDialogJanela-botoes"></div>');
		for (x in opcoes.botoes) {
			// cria HTML do botão
			$('#m2brDialogJanela-botoes').append('<a href="#" id="m2brDialog-botao-'+x+'" class="m2brDialogBotao">'+opcoes.botoes[x].label+'</a>');
			// ação - fechar janela
			if (opcoes.botoes[x].tipo == 'fechar') {
				$('#m2brDialog-botao-'+x).click(fechaJanela);
			// ação - redirecionamento para link
			} else if (opcoes.botoes[x].tipo == 'link') {
				$('#m2brDialog-botao-'+x).attr('href', opcoes.botoes[x].endereco);	
			// ação - função javascript
			} else if (opcoes.botoes[x].tipo == 'script') {
				$('#m2brDialog-botao-'+x).click( opcoes.botoes[x].funcao );
				$('#m2brDialog-botao-'+x).click( fechaJanela );
			}
		} // fim for
		
		// impede múltiplos cliques no mesmo botão
		$('#m2brDialogJanela-botoes a').click(function() { 
			$('#m2brDialogFechar').hide();
			$('#m2brOverlay').unbind('click');
			$('#m2brDialogJanela-botoes').html('<div class="carregando"></a>');
		});
		
		// define timeOut
		if (!isNaN(opcoes.tempoExibicao) && opcoes.tempoExibicao > 0) { m2brTimer = setTimeout(fechaJanela, opcoes.tempoExibicao*1000); }

		return false;		
		
	}); // fim this.click
	
	// fecha janela
	var fechaJanela = function() {		
		// remove binds
		$('#m2brOverlay').unbind('click');
		// limpa timeout
		clearTimeout(m2brTimer);
		// remove overlay + janela
		$('#m2brOverlayFixed').remove();
		$('#m2brDialogJanelaFixed').remove();
		// exibe selects
		$('select').each(function(){ $(this).show(); });
		return false;
	}; // fim fechaJanela

}; // fim fn