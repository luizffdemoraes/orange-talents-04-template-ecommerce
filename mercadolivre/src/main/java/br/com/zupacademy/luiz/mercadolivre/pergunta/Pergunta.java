package br.com.zupacademy.luiz.mercadolivre.pergunta;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import br.com.zupacademy.luiz.mercadolivre.produto.Produto;
import br.com.zupacademy.luiz.mercadolivre.usuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank String titulo;
	@ManyToOne
	private @NotNull Usuario usuarioLogado;
	@ManyToOne
	private @NotNull Produto produto;
	private LocalDate instante;

	@Deprecated
	public Pergunta() {
	}

	public Pergunta(String titulo, Usuario usuarioLogado, Produto produto) {

		this.titulo = titulo;
		this.usuarioLogado = usuarioLogado;
		this.produto = produto;
		this.instante = LocalDate.now();

	}


	@Override
	public String toString() {
		return "Pergunta [id=" + id + ", titulo=" + titulo + ", usuarioLogado=" + usuarioLogado + ", produto=" + produto
				+ "]";
	}

	public Usuario getInteressada() {

		return usuarioLogado;
	}

	public Usuario getDonoProduto() {
		return produto.getDono();
	}

}
