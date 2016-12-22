package br.com.rodolfo.validation;

import java.math.BigDecimal;

import org.springframework.validation.Errors;

import br.com.rodolfo.model.Titulo;

public class BeanValidation {

	
	public static void getErrors (Errors errors, Titulo titulo) {
		
		
		if (titulo.getDescricao() == null || titulo.getDescricao().trim().equals("")) {
			errors.rejectValue("descricao", null, "A descrição é obrigatória");
		}
		
		if (titulo.getDataVencimento() == null ) {
			errors.rejectValue("dataVencimento", null, "A data de vencimento é obrigatória");
		}
		
		
		if (titulo.isBranco()) {
			errors.rejectValue("status", null, "Status é obrigatório");
		}
		
		if (titulo.getValor() == null ) {
			errors.rejectValue("valor", null, "O valor de real é obrigatório");
		} else if (titulo.getValor().compareTo(new BigDecimal("0.00")) < 0 ) {
			errors.rejectValue("valor", null, "Menor valor permitido em Valor Real é R$0,00");
		}
		
		
		
		if (titulo.isRecebido()) {
			
			if (titulo.isValorPagamentoValido()) {			
				errors.rejectValue("valorPago", null, "Valor de pagamento está menor que o valor real");	
			}
			
			if (titulo.getDataPagamento() == null) {				
				errors.rejectValue("dataPagamento", null, "A data de pagamento é obrigatória");							
			}
			
			if(titulo.getValorPago() == null) {			
				errors.rejectValue("valorPago", null, "O valor de pagamento é obrigatório");		
			}
						
			
		}			
		
	}
	
	
}
