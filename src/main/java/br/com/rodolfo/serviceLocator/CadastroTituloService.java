package br.com.rodolfo.serviceLocator;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.rodolfo.model.StatusTitulo;
import br.com.rodolfo.model.Titulo;
import br.com.rodolfo.repository.Titulos;
import br.com.rodolfo.repository.filter.TituloFiltro;

@Service
public class CadastroTituloService {

	@Autowired
	private Titulos titulos;
	
	private final int ANO_INICIO = 90;
	private final int MES_INICIO = 0;
	private final int DIA_INICIO = 1;
	private final int DIA_FIM = 31;
	private final int MES_FIM = 11;
	
	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);
		} catch(DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato da datava inválido");
		}
	}
	
	public void excluir(Long codigo) {
		titulos.delete(codigo);
	}
	
	public List<Titulo> pesquisarPelaData(TituloFiltro filtro) {
		
		Date dataIniFiltroNulo = new Date(ANO_INICIO, MES_INICIO, DIA_INICIO);
		Date dataFimFiltroNulo = new Date();
		
		dataFimFiltroNulo.setDate(DIA_FIM);
		dataFimFiltroNulo.setMonth(MES_FIM);
		
		Date ini = filtro.getIni() == null ? dataIniFiltroNulo : filtro.getIni();
		Date fim = filtro.getFim() == null ? dataFimFiltroNulo : filtro.getFim();
		
		return titulos.findByDataVencimentoBetweenOrderByDataVencimento(ini, fim);
	}
	
	public List<Titulo> pesquisarTudo () {
		return titulos.findByDescricaoContaining("");
	}
	
	public String receber(Long codigo) {
		Titulo titulo = titulos.findOne(codigo);
		titulo.setStatus(StatusTitulo.RECEBIDO);
		titulos.save(titulo);
		
		return StatusTitulo.RECEBIDO.getDescricao();
	}
	
}
