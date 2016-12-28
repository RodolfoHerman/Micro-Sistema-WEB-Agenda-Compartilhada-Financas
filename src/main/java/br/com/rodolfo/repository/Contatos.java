package br.com.rodolfo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodolfo.model.Contato;

public interface Contatos extends JpaRepository <Contato, Long>{

	public List<Contato> findByNomeIgnoreCaseContainingOrderByNome(String nome);
	
}
