package br.com.zupacademy.luiz.mercadolivre.seguranca.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExisteIdValidador.class)
@Documented
public @interface ExisteId {
    String message() default "Dado informado ${domainClass.getSimpleName()} com o id ${validatedValue. Não existe.}";
    // aplicar validacao apenas para grupos especificos
    Class<?>[] groups() default {};
    // mandar informacao a mais para a validacao...
    Class<? extends Payload>[] payload() default {};
    // aqui vem basicamente o que a anotacao vai precisar para validar
    //String fieldName();
    Class<?> targetClass();
    boolean shoudExist() default false;
}