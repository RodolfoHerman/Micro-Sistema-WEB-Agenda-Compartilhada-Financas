package br.com.rodolfo.validation;

import org.springframework.validation.Errors;

import br.com.rodolfo.model.Contato;

public class ContatoBeanValidation {

	
	public static void getErrors (Contato contato, Errors errors) {
		
		if (contato.getNome() == null || contato.getNome().trim().equals("")) {
			errors.rejectValue("nome", null, "Nome é obrigatório");
		}
		
		if (contato.getNome().length() > 180) {
			errors.rejectValue("nome", null, "O número máximo de caracteres no nome é 180");
		}
		
		if (contato.getNome().trim().length() == 1) {
			
			errors.rejectValue("nome", null, "É obrigatório nome de tamanho maior do que 1");
			
		}
		
		
		if ((contato.getTelefoneCel() == null || contato.getTelefoneCel().trim().equals("") && 
			(contato.getTelefoneRes() == null || contato.getTelefoneRes().trim().equals(""))) ) {
			
			errors.rejectValue("telefoneRes", null, "É necessário pelo menos um telefone cadastrado");
			errors.rejectValue("telefoneCel", null, "");
			
			
		}
		

		if ((contato.getTelefoneRes() != null) && (!contato.getTelefoneRes().trim().equals("")) && 
				(contato.getTelefoneRes().trim().length() <= 12)) {
			
			errors.rejectValue("telefoneRes", null, "O número residencial está errado");
			
		}
		
		if ((contato.getTelefoneCel() != null) && (!contato.getTelefoneCel().trim().equals("")) && 
				(contato.getTelefoneCel().trim().length() <= 13)) {
			
			errors.rejectValue("telefoneCel", null, "O número do celular está errado");
			
		}		
		
		
	}
	
	
}
