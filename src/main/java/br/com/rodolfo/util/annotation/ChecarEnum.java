package br.com.rodolfo.util.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = {ValidarValorEnum.class})
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ChecarEnum {

	String message() default "{br.com.rodolfo.util.ChecarEnum.}";
	
	Class<?>[] groups() default {};
	  
    Class<? extends Payload>[] payload() default {};
	
    String [] values();
    
}

