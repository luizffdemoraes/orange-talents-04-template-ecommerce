package br.com.zupacademy.luiz.mercadolivre.produto.detalhe;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.SortedSet;
import java.util.stream.IntStream;

import br.com.zupacademy.luiz.mercadolivre.produto.Produto;
import br.com.zupacademy.luiz.mercadolivre.produto.opiniao.Opiniao;


public class DetalheProdutoResponse {

	private String descricao;
	private String nome;
	private BigDecimal preco;
	private Set<DetalheProdutoCaracteristica> caracteristicas;
	private Set<String> linksImagens;
	private SortedSet<String> perguntas;
	private Set<Map<String, String>> opinioes;
	private double mediaNotas;

	public DetalheProdutoResponse(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		// 3 opção
		this.caracteristicas = produto.mapeiaCaracteristicas(DetalheProdutoCaracteristica::new);
		this.linksImagens = produto.mapeiaImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapeiaPerguntas(pergunta -> pergunta.getTitulo());
		this.opinioes = produto.mapeiaOpinioes(opiniao -> {
			return Map.of("titulo",opiniao.getTitulo(),"descricao",opiniao.getDescricao());
		});
		
		Set<Integer> notas = produto.mapeiaOpinioes(opiniao ->  opiniao.getNota());
		
		OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
//		if(possivelMedia.isPresent()) {
//			this.mediaNotas = possivelMedia.getAsDouble();
//		}
		
		this.mediaNotas = possivelMedia.orElseGet(()-> 0.0);
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Set<DetalheProdutoCaracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public SortedSet<String> getPerguntas() {
		return perguntas;
	}

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}
	
	public double getMediaNotas() {
		return mediaNotas;
	}

	
}


// 2 opção
//produto.mapCaracteristicas(caracteristica -> new DetalheProdutoCaracteristica(
//		caracteristica));

// 1 opção
//.getCaracteristicas().stream()
//.map(caracteristica -> new DetalheProdutoCaracteristica(
//		caracteristica)).collect(Collectors.toSet());