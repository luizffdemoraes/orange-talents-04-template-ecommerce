package br.com.zupacademy.luiz.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.luiz.mercadolivre.usuario.Usuario;
import br.com.zupacademy.luiz.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping
    @Transactional
    public ResponseEntity<?> criaProduto(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario usuarioLogado){
		Produto novoProduto = request.toModel(manager, usuarioLogado);
        manager.persist(novoProduto);
        return ResponseEntity.ok().build();
    }

}
