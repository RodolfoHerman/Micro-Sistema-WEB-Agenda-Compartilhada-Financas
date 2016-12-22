package br.com.rodolfo.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Entity
@Table (name = "Titulos")
public class Titulo {

	private Long codigo;
	private String descricao;
	private Date dataPagamento;
	private Date dataVencimento;
	private BigDecimal valor;
	private BigDecimal valorPago;
	private StatusTitulo status;
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	//@Size (max = 60, message = "A descrição não pode possuir mais do que 60 caracteres")
	@NotEmpty (message = "A descrição é obrigatória")
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	@Temporal (TemporalType.DATE)
	@DateTimeFormat (pattern = "dd/MM/yyyy")
	public Date getDataPagamento() {
		return dataPagamento;
	}
	
	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	//@NotNull (message = "A data de vencimento é obrigatória")
	@Temporal (TemporalType.DATE)
	@DateTimeFormat (pattern = "dd/MM/yyyy")
	public Date getDataVencimento() {
		return dataVencimento;
	}
	
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	
	//@NotNull (message = "O valor real é obrigatório")
	//@DecimalMin (value = "0.00", message = "Menor valor permitido em Valor Real é R$0,00")
	//@DecimalMax (value = "1000000.00", message = "Maior valor permitido é R$1.000.000,00")
	@NumberFormat (pattern = "#,##0.00")
	public BigDecimal getValor() {
		return valor;
	}
	
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	
	//@DecimalMin (value = "0.00", message = "Menor valor permitido em Valor de Pagamento é R$0,00")
	//@DecimalMax (value = "1000000.00", message = "Maior valor permitido é R$1.000.000,00")
	@NumberFormat (pattern = "#,##0.00")
	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	//@ChecarEnum(values = {"Pendente", "Pago"},	message = "Status é obrigatório")
	@Enumerated (EnumType.STRING)
	public StatusTitulo getStatus() {
		return status;
	}
	
	public void setStatus(StatusTitulo status) {
		this.status = status;
	}
	
	@Transient
	public boolean isPendente () {
		return (this.status == null ? false :
			StatusTitulo.PENDENTE.equals(this.status));
	}
	
	@Transient
	public boolean isRecebido () {
		return (this.status == null ? false : 
			StatusTitulo.RECEBIDO.equals(this.status));
	}	
	
	@Transient
	public boolean isBranco () {
		return (this.status == null ? true : 
			StatusTitulo.BRANCO.equals(this.status));
	}
	
	@Transient
	public boolean isValorPagamentoValido() {
		return (valor == null || valorPago == null ? 
				false : valorPago.compareTo(valor) < 0);
	}
	
	@Transient
	public String getDataVencimentoString() {
		return this.dataVencimento.toString();
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
		Titulo other = (Titulo) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	
	
}
