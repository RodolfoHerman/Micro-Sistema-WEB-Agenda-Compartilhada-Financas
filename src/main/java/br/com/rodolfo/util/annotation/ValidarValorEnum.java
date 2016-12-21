package br.com.rodolfo.util.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.thymeleaf.util.ArrayUtils;

import br.com.rodolfo.model.StatusTitulo;

public class ValidarValorEnum implements ConstraintValidator <ChecarEnum, StatusTitulo>{

	
	private String [] values;

	
	@Override
	public void initialize(ChecarEnum values) {
		
		this.values = values.values();
		
	}

	@Override
	public boolean isValid(StatusTitulo value, ConstraintValidatorContext context) {
		
		return ArrayUtils.contains(this.values, value == null ? false : value.getDescricao());

	}

}