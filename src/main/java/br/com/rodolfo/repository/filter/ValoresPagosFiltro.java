package br.com.rodolfo.repository.filter;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.stereotype.Component;

@Component
public class ValoresPagosFiltro {

	@NumberFormat (pattern = "#,##0.00")
	private BigDecimal valorPagoTotal;
	
	@NumberFormat (pattern = "#,##0.00")
	private BigDecimal valorRealTotal;
	
	@NumberFormat (pattern = "#,##0.00")
	private BigDecimal valorJuroTotal;

	public BigDecimal getValorPagoTotal() {
		return valorPagoTotal;
	}

	public void setValorPagoTotal(BigDecimal valorPagoTotal) {
		this.valorPagoTotal = valorPagoTotal;
	}

	public BigDecimal getValorRealTotal() {
		return valorRealTotal;
	}

	public void setValorRealTotal(BigDecimal valorRealTotal) {
		this.valorRealTotal = valorRealTotal;
	}

	public BigDecimal getValorJuroTotal() {
		return valorJuroTotal;
	}

	public void setValorJuroTotal(BigDecimal valorJuroTotal) {
		this.valorJuroTotal = valorJuroTotal;
	}
	
	
}
