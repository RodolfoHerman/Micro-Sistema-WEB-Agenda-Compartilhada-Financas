package br.com.rodolfo.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rodolfo.model.Titulo;

public interface Titulos extends JpaRepository <Titulo, Long>{

	
	public List<Titulo> findByDescricaoContaining(String descricao);
	
	public List<Titulo> findByDataVencimentoBetweenOrderByDataVencimento (Date ini, Date fim);
	
}
