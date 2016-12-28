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



$(function(){
	
	$('[name="button"]').tooltip();
	$('[rel="tooltip"]').tooltip();
	$('.js-formatar-valor').maskMoney({decimal:",", thousands:".", allowZero:"false"});
	
});

$(function(){
	
	$('.js-verificar-selecao').on('change', function (event){
		
		var selecao = event.currentTarget.value;
		
		if (selecao == 'RECEBIDO') {
			document.getElementById('div_dataPg').style.display = "block";
			document.getElementById('div_valorPg').style.display = "block";
		    
		} else {
			document.getElementById('div_dataPg').style.display = "none";
			document.getElementById('div_valorPg').style.display = "none";
		}
		
		
	});
	
	
	$('.js-botao-excluir').on('click', function(){
		
		let btnTitulos = 'btnExcluirPesquisaTitulos';
		var id = jQuery(this).attr("id");
		
		var modal = $('#confirmacaoExclusaoModal');
		var form = modal.find('form');
		var src = form.attr('data-url-base');
		
		if (id == btnTitulos) {
			
			var dst = src.substring(0, src.lastIndexOf("/")+1) + "titulos";

			form.attr('data-url-base', dst);
			
		} else {
			
			
			var dst = src.substring(0, src.lastIndexOf("/")+1) + "agenda";

			form.attr('data-url-base', dst);
		}
		
		
	});	
	
	$('.js-limpar-campos').on('click', function(event){
		
		event.preventDefault();
		
		$('#formPesquisa').find("input[type=text]").val(null);
		$('#formPesquisa').trigger('submit');
	});
	
});