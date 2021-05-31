package br.com.zupacademy.luiz.mercadolivre.categoria;

import java.io.Serializable;
import java.util.Optional;
import javax.validation.constraints.NotBlank;
import br.com.zupacademy.luiz.mercadolivre.seguranca.validacao.ExisteId;
import br.com.zupacademy.luiz.mercadolivre.seguranca.validacao.UnicoValor;



public class CategoriaRequest implements Serializable{
	private static final long serialVersionUID = 1L;

	@NotBlank
	@UnicoValor(targetClass = Categoria.class, field = "nome", message = "Já existe uma categoria cadastrada com esse nome.")
	private String nome;

	@ExisteId(targetClass = Categoria.class, field = "id")
	private Long categoriaMaeId;

	public String getNome() {
		return nome;
	}

	public Long getCategoriaMaeId() {
		return categoriaMaeId;
	}

	public Categoria toModel(CategoriaRepository categoriaRepository) {
		if(categoriaMaeId == null) return new Categoria(this.nome);
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaMaeId);
		return new Categoria(this.nome, categoria.get());
	}
	
	
	
	

}
