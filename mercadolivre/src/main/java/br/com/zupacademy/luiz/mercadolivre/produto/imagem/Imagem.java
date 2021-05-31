package br.com.zupacademy.luiz.mercadolivre.produto.imagem;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

import br.com.zupacademy.luiz.mercadolivre.produto.Produto;

@Entity
public class Imagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@NotNull
	@Valid
	private Produto produto;
	@URL
	@NotBlank
	private String link;

	@Deprecated
	public Imagem() {
	}

	public Imagem(@NotNull @Valid Produto produto, @URL String link) {
		this.produto = produto;
		this.link = link;
	}

	@Override
	public String toString() {
		return "Imagem [id=" + id + ", link=" + link + "]";
	}

}
