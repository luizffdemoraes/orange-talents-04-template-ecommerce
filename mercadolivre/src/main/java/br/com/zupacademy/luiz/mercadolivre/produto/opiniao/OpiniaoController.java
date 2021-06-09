package br.com.zupacademy.luiz.mercadolivre.produto.opiniao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.luiz.mercadolivre.produto.Produto;
import br.com.zupacademy.luiz.mercadolivre.usuario.Usuario;

@RestController
public class OpiniaoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/produtos/{id}/opiniao")
	@Transactional
	public ResponseEntity<?> cadastrarOpiniao(@RequestBody @Valid OpiniaoRequest request, 
								 @PathVariable("id") Long id, 
								 @AuthenticationPrincipal Usuario usuarioLogado){
		
		Produto produto = manager.find(Produto.class, id);
		Opiniao novaOpiniao = request.toModel(produto, usuarioLogado);
		manager.persist(novaOpiniao);
		return ResponseEntity.ok().body(novaOpiniao.toString());
	}
	

}
