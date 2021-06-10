package br.com.zupacademy.luiz.mercadolivre.pergunta;


import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.luiz.mercadolivre.produto.Produto;
import br.com.zupacademy.luiz.mercadolivre.usuario.Usuario;

public class PerguntaRequest {
	
	@NotBlank
	private String titulo;
	
	

	public PerguntaRequest() {
	}

	public PerguntaRequest(String titulo) {
		this.titulo = titulo;
	}

	public Pergunta toModel(@NotNull @Valid Usuario usuarioLogado,@NotNull @Valid Produto produto) {
		return new Pergunta(titulo, usuarioLogado, produto);
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}
	
	
	
	
	

}
