package br.com.zupacademy.luiz.mercadolivre.pergunta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import br.com.zupacademy.luiz.mercadolivre.produto.Produto;
import br.com.zupacademy.luiz.mercadolivre.usuario.Usuario;

@RestController
public class PerguntaController {

	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private Emails emails;

	@PostMapping(value = "/produtos/{id}/perguntas")
	@Transactional
	public ResponseEntity<?> cadastrarPerguntas(@RequestBody @Valid PerguntaRequest request,
			@PathVariable("id") Long id, @AuthenticationPrincipal Usuario usuarioLogado) {

		
		Produto produto = manager.find(Produto.class, id);
		Pergunta novaPergunta = request.toModel(usuarioLogado, produto);
		manager.persist(novaPergunta);
		
	
		emails.novaPergunta(novaPergunta);
		
		
		return ResponseEntity.ok().body(novaPergunta.toString());

	}

}
