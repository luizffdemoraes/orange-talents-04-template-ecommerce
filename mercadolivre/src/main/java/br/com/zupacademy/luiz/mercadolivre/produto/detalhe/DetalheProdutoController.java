package br.com.zupacademy.luiz.mercadolivre.produto.detalhe;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.luiz.mercadolivre.produto.Produto;

@RestController
public class DetalheProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping(value = "/produtos/{id}")
	private DetalheProdutoResponse getMethodName(@PathVariable("id") Long id) {

		Produto produtoEscolhido = manager.find(Produto.class, id);
		
		return new DetalheProdutoResponse(produtoEscolhido);

	}
}
