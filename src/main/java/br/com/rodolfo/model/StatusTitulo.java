package br.com.rodolfo.model;

public enum StatusTitulo {

	BRANCO (""),
	RECEBIDO ("Pago"),
	PENDENTE ("Pendente");
	
	private final String descricao;
	
	StatusTitulo (String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao () {
		return (this.descricao);
	}
	
}
