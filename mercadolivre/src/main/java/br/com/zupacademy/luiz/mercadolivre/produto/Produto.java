package br.com.zupacademy.luiz.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.Valid;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import io.jsonwebtoken.lang.Assert;

import br.com.zupacademy.luiz.mercadolivre.categoria.Categoria;
import br.com.zupacademy.luiz.mercadolivre.pergunta.Pergunta;
import br.com.zupacademy.luiz.mercadolivre.produto.caracteristica.Caracteristica;
import br.com.zupacademy.luiz.mercadolivre.produto.caracteristica.CaracteristicaRequest;
import br.com.zupacademy.luiz.mercadolivre.produto.opiniao.Opinioes;
import br.com.zupacademy.luiz.mercadolivre.produto.imagem.Imagem;
import br.com.zupacademy.luiz.mercadolivre.produto.opiniao.Opiniao;
import br.com.zupacademy.luiz.mercadolivre.usuario.Usuario;

@Entity
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private BigDecimal valor;

	@Column(nullable = false)
	private Integer quantidade;

	@Size(min = 3)
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<Caracteristica> caracteristicas = new HashSet<>();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Imagem> imagens = new HashSet<Imagem>();
	@OneToMany(mappedBy = "produto")
	@OrderBy("titulo asc")
	private SortedSet<Pergunta> perguntas = new TreeSet<>();
	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<Opiniao> opinioes = new HashSet<>();

	@Column(nullable = false)
	private String descricao;

	@ManyToOne
	@Valid
	private Categoria categoria;

	@ManyToOne
	@Valid
	private Usuario usuario;

	@CreationTimestamp
	@Column(nullable = false)
	private Instant criadoEm = Instant.now();

	@Deprecated
	public Produto() {
	}

	public Produto(String nome, BigDecimal valor, Integer quantidade, String descricao, Categoria categoria,
			Collection<CaracteristicaRequest> caracteristicas, Usuario usuario) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		this.caracteristicas.addAll(caracteristicas.stream().map(c -> c.toModel(this)).collect(Collectors.toSet()));

		Assert.isTrue(this.caracteristicas.size() >= 3, "Todo produto precisa ter no minimo 3 caracteristicas.");
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", valor=" + valor + ", quantidade=" + quantidade
				+ ", caracteristicas=" + caracteristicas + ", imagens=" + imagens + ", descricao=" + descricao
				+ ", categoria=" + categoria + ", usuario=" + usuario + ", criadoEm=" + criadoEm + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	public void associaImagens(Set<String> links) {
		Set<Imagem> imagens = links.stream().map(link -> new Imagem(this, link)).collect(Collectors.toSet());
		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario possivelDono) {
		return this.usuario.equals(possivelDono);
	}

	public Usuario getDono() {
		return this.usuario;
	}

	public Long getId() {
		return id;
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

	public Set<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public Set<Imagem> getImagens() {
		return imagens;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Instant getCriadoEm() {
		return criadoEm;
	}

	public <T> Set<T> mapeiaCaracteristicas(Function<Caracteristica, T> funcaoMapeadora) {
		return this.caracteristicas.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T> Set<T> mapeiaImagens(Function<Imagem, T> funcaoMapeadora) {
		return this.imagens.stream().map(funcaoMapeadora).collect(Collectors.toSet());
	}

	public <T extends Comparable<T>> SortedSet<T> mapeiaPerguntas(Function<Pergunta, T> funcaoMapeadora) {
		return this.perguntas.stream().map(funcaoMapeadora).collect(Collectors.toCollection(TreeSet::new));
	}

	public Opinioes getOpinioes() {

		return new Opinioes(this.opinioes);
	}

	public boolean abataEstoque(@Positive int quantidade) {
		Assert.isTrue(quantidade > 0, "A quantidade deve ser maior que zero para abater o estoque " + quantidade);

		if (quantidade <= this.quantidade) {
			this.quantidade -= quantidade;
			return true;
		}

		return false;

	}

}
