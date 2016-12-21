package br.com.rodolfo.repository.filter;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class TituloFiltro {
	
	@Temporal (TemporalType.DATE)
	@DateTimeFormat (pattern = "dd/MM/yyyy")
	private Date ini;
	
	@Temporal (TemporalType.DATE)
	@DateTimeFormat (pattern = "dd/MM/yyyy")
	private Date fim;

	public Date getIni() {
		return ini;
	}

	public void setIni(Date ini) {
		this.ini = ini;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

	
}