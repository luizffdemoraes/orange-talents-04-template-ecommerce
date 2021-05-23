package br.com.zupacademy.luiz.mercadolivre.seguranca.validacao;

import java.lang.annotation.*;


import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UnicoValorValidador.class)
@Documented
public @interface UnicoValor {
	
	Class<?> targetClass();
	
	String field();
	
	//mensagem default que será aplicada
    String message() default "Critério de valor único violado.}";
    // aplicar validacao apenas para grupos especificos
    Class<?>[] groups() default {};
    // mandar informacao a mais para a validacao...
    Class<? extends Payload>[] payload() default {};

}
