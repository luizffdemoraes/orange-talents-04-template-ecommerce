package br.com.zupacademy.luiz.mercadolivre.produto.opiniao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import br.com.zupacademy.luiz.mercadolivre.produto.Produto;
import br.com.zupacademy.luiz.mercadolivre.usuario.Usuario;

@Entity
public class Opiniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Min(1) @Max(5)
	private Integer nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Column(length = 500)
	private String descricao;
	@ManyToOne
	private Produto produto;
	@ManyToOne
	private Usuario consumidor;
	
	public Opiniao(@Min(1) @Max(5) Integer nota, @NotBlank String titulo, @NotBlank String descricao, Produto produto,
			Usuario consumidor) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.produto = produto;
		this.consumidor = consumidor;
	}

	@Override
	public String toString() {
		return "Opiniao [id=" + id + ", nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + ", produto="
				+ produto + ", consumidor=" + consumidor.getUsername() + "]";
	}
	
	
	

}
