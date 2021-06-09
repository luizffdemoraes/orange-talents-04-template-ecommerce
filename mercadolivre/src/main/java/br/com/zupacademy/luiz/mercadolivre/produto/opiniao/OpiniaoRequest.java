package br.com.zupacademy.luiz.mercadolivre.produto.opiniao;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.luiz.mercadolivre.produto.Produto;
import br.com.zupacademy.luiz.mercadolivre.usuario.Usuario;

public class OpiniaoRequest {
	
	@Min(1)
	@Max(5)
	private int nota;
	@NotBlank
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String descricao;
	
	public OpiniaoRequest(int nota, 
						  String titulo,
						  String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Opiniao toModel(@NotNull @Valid Produto produto,@NotNull @Valid Usuario usuarioLogado) {
		return new Opiniao(nota, titulo, descricao, produto, usuarioLogado);
		
	}


	
	
	
	

}
