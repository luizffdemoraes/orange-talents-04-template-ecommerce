package br.com.zupacademy.luiz.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import br.com.zupacademy.luiz.mercadolivre.categoria.Categoria;
import br.com.zupacademy.luiz.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zupacademy.luiz.mercadolivre.seguranca.validacao.ExisteId;
import br.com.zupacademy.luiz.mercadolivre.usuario.Usuario;

public class ProdutoRequest {

	@NotBlank
	private String nome;
	@NotNull
	@Positive
	private BigDecimal valor;
	@NotNull
	@Positive
	private Integer quantidade;
	@NotBlank
	@Length(max = 1000)
	private String descricao;
	@ExisteId(required = true, targetClass = Categoria.class, field = "id")
	private Long idCategoria;
	@NotNull
	@Size(min = 3)
	private Set<CaracteristicaRequest> caracteristicas;

	public ProdutoRequest(String nome, BigDecimal valor, Integer quantidade, String descricao, Long idCategoria,
			Set<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas = caracteristicas;
	}

	public Produto toModel(EntityManager manager, Usuario usuario) {
		Categoria categoria = manager.find(Categoria.class, idCategoria);
		Assert.notNull(categoria, "Categoria não deveria ser nula");

		Usuario usuarioLogado = manager.find(Usuario.class, usuario.getId());
		Assert.notNull(usuarioLogado, "Usuário logado não foi encontrado");

		return new Produto(this.nome, this.valor, this.quantidade, this.descricao, categoria, caracteristicas,
				usuarioLogado);
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public Set<CaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}

}
