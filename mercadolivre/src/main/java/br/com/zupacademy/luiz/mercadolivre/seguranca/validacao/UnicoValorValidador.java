package br.com.zupacademy.luiz.mercadolivre.seguranca.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnicoValorValidador implements ConstraintValidator<UnicoValor, Object> {

	private String field;
	private Class<?> targetClass;

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(UnicoValor params) { 
		field = params.field();
		targetClass = params.targetClass();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
		Query query = manager
				.createQuery("select 1 from " + targetClass.getName() + " where " + field + "=:pValue");
		query.setParameter("pValue", value);
		List<?> list = query.getResultList();

		return list.isEmpty();

	}

}
