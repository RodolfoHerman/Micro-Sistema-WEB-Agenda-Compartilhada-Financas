package br.com.rodolfo.util;

import java.math.BigDecimal;
import java.util.List;

import br.com.rodolfo.model.Titulo;
import br.com.rodolfo.repository.filter.ValoresPagosFiltro;

public class Processamento {

	public static ValoresPagosFiltro CalcularValoresPagosRealJuros (List<Titulo> consulta) {
		
		ValoresPagosFiltro valoresPagosFiltro = new ValoresPagosFiltro();
		
		BigDecimal valorPago = new BigDecimal("0.0");
		BigDecimal valorJuro = new BigDecimal("0.0");
		BigDecimal valorReal = new BigDecimal("0.0");
		
		for (Titulo titulo : consulta) {
			
			
			
			if (titulo.isRecebido() && titulo.getValorPago() != null) {
				
				valorPago = valorPago.add(titulo.getValorPago());
				valorJuro = valorJuro.add(titulo.getValorPago()).subtract(titulo.getValor());
				
			}
			
			valorReal = valorReal.add(titulo.getValor());
			
			
		}
		
		valoresPagosFiltro.setValorPagoTotal(valorPago);
		valoresPagosFiltro.setValorJuroTotal(valorJuro);
		valoresPagosFiltro.setValorRealTotal(valorReal);
		
		return (valoresPagosFiltro);
	}
	
	
	
}
