function excluir(dom) {

	let btnTitulos = 'btnExcluirPesquisaTitulos';
	var name = dom.getAttribute("name");
		
	var modal = $('#confirmacaoExclusaoModal');
	var form = modal.find('form');
	var src = form.attr('data-url-base');
		
	if (name == btnTitulos) {
		
		var dst = src.substring(0, src.lastIndexOf("/")+1) + "titulos";
		form.attr('data-url-base', dst);		
	} else {
			
		var dst = src.substring(0, src.lastIndexOf("/")+1) + "agenda";
		form.attr('data-url-base', dst);
	}
}


$(document).ready(function () {	

	$('#confirmacaoExclusaoModal').on('show.bs.modal', function (event){
		
		var button = $(event.relatedTarget);
		var codigo = button.data('codigo');
		var descricao = button.data('descricao');
		
		var modal = $(this);
		var form = modal.find('form');
		var actionText = form.data('url-base');
		
		console.log(actionText);
		
		if (!actionText.endsWith('/')) {
			
			actionText = actionText + '/';

		}
		
		form.attr ('action', actionText + codigo);
		
		modal.find('.modal-body span').html('Tem certeza que quer excluir <strong>' + descricao + '</strong> ?');
		
	});

	//Adiciona um tooltipo ao elemento, a selecao acontece pelo atributo name e pela atributo rel
	$('[name="button"]').tooltip();
	$('[rel="tooltip"]').tooltip();
	//Utiza-se o seletor de classe para atribuir a mascara aos elementos
	$('.js-formatar-valor').maskMoney({decimal:",", thousands:".", allowZero:"false"});


	//Quando ha mudanca no componete select, verifica-se seu estado e realizada as alteracoes.
	$('#verificar-selecao').on('change', function() {
		
		var selecao = $(this).val();
		
		if (selecao == 'RECEBIDO') {
			$('#div_dataPg').show();
			$('#div_valorPg').show();
		    
		} else {
			$('#div_dataPg').val("").hide();
			$('#div_valorPg').val("").hide();
		}
		
	});	
	
	$('#limpar-campos').on('click', function(event){
		
		event.preventDefault();
		
		$('#formPesquisa').find("input[type=text]").val(null);
		$('#formPesquisa').trigger('submit');
	});	

}