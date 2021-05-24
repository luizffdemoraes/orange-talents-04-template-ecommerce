package br.com.zupacademy.luiz.mercadolivre.seguranca.validacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExisteIdValidador implements ConstraintValidator<ExisteId, Object> {

	@PersistenceContext
	private EntityManager manager;

	private Class<?> targetClass;

	private boolean shoudExist;

	@Override
	public void initialize(ExisteId params) {
		targetClass = params.targetClass();
		shoudExist = params.shoudExist();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
		
		if (shoudExist && value == null)
			return true;
		
		return !manager.createQuery("select 1 from " + targetClass.getName() + " where id = :value")
				.setParameter("value", value)
				.getResultList()
				.isEmpty();

	}
}