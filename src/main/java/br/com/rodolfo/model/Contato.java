package br.com.rodolfo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Contato {

	private Long codigo;
	private String nome;
	private String telefoneRes;
	private String telefoneCel;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@NotEmpty (message = "O nome é obrigatório")
	public String getNome() {
		return nome;
	}
	
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	//@Pattern(regexp = "^?[ ]\\(?(\\d{2})\\)?(\\d{4})[- ]?(\\d{4})$")
	public String getTelefoneRes() {
		return telefoneRes;
	}
	
	
	public void setTelefoneRes(String telefoneRes) {
		this.telefoneRes = telefoneRes;
	}
	
	//@Pattern(regexp = "^?[ ]\\(?(\\d{2})\\)?(\\d{5})[- ]?(\\d{4})$")
	public String getTelefoneCel() {
		return telefoneCel;
	}
	
	
	public void setTelefoneCel(String telefoneCel) {
		this.telefoneCel = telefoneCel;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Contato other = (Contato) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
	
}
