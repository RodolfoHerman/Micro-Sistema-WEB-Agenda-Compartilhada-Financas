package br.com.rodolfo.serviceLocator;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rodolfo.model.Contato;
import br.com.rodolfo.repository.Contatos;

@Service
public class ContatoServiceLocator {

	@Autowired
	private Contatos contatos;
	
	
	public void salvar (Contato contato) {
		contatos.save(contato);
	}
	
	public List<Contato> pesquisar (String nome) {
		
		String notNull = nome == null ? "" : nome;
		
		return contatos.findByNomeIgnoreCaseContainingOrderByNome(notNull);
	}
	
	public void excluir (Long codigo) {
		contatos.delete(codigo);
	}
	
}
