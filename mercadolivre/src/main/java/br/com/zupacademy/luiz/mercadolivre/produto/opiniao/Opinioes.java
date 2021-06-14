package br.com.zupacademy.luiz.mercadolivre.produto.opiniao;

import java.util.OptionalDouble;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Isola as operaçoes sobre um conjunto de opinioes
 * @author luiz.moraes
 *
 */


public class Opinioes {

	private Set<Opiniao> opinioes;

	
	@Deprecated
	public Opinioes() {
	}



	public Opinioes(Set<Opiniao> opinioes) {
		this.opinioes = opinioes;
	
	}
	
	public<T> Set<T> mapeiaOpinioes(Function<Opiniao, T> funcaoMapeadora) {
		return this.opinioes.stream().map(funcaoMapeadora)
				.collect(Collectors.toSet());
	}



	public double media() {
		Set<Integer> notas = mapeiaOpinioes(opiniao ->  opiniao.getNota());
		OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
		return possivelMedia.orElseGet(()-> 0.0);
	}

	public int total() {
		return this.opinioes.size();
	}
}
