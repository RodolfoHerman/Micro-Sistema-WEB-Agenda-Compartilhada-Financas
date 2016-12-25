$('#confirmacaoExclusaoModal').on('show.bs.modal', function (event){
	
	var button = $(event.relatedTarget);
	var codigo = button.data('codigo');
	var descricao = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var actionText = form.data('url-base');
	
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
	
	$('.js-limpar-campos').on('click', function(event){
		
		event.preventDefault();
		
		$('#formPesquisa').find("input[type=text]").val(null);
		$('#formPesquisa').trigger('submit');
	});
	
});