package br.com.zupacademy.luiz.mercadolivre.produto;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.zupacademy.luiz.mercadolivre.produto.imagem.ImagensRequest;
import br.com.zupacademy.luiz.mercadolivre.produto.imagem.UploaderFake;
import br.com.zupacademy.luiz.mercadolivre.usuario.Usuario;
import br.com.zupacademy.luiz.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {
	
	@PersistenceContext
	private EntityManager manager;
	@Autowired
	private UploaderFake uploaderFake;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@PostMapping
    @Transactional
    public ResponseEntity<?> criaProduto(@RequestBody @Valid ProdutoRequest request, @AuthenticationPrincipal Usuario usuarioLogado){
		Produto novoProduto = request.toModel(manager, usuarioLogado);
        manager.persist(novoProduto);
        return ResponseEntity.ok().build();
    }
	
	/*
	 * 1) Enviar imanges para o local onde elas vão ficar
	 * 2) Pegar os links de todas as imagens
	 * 3) associar esses links com o produto em questão
	 * 4) preciso carregar o produto
	 * 5) depois que associar eu preciso atualizar a nova versão do produto
	 */

	@PostMapping("/{id}/imagens")
    @Transactional
    public String adicionaImagens(@PathVariable("id") Long id,@Valid ImagensRequest request){
		Usuario dono = usuarioRepository.findByLogin("luiz@gmail.com").get();
		Produto produto = manager.find(Produto.class, id);
		
		if(!produto.pertenceAoUsuario(dono)) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
			
		}
		
		
		Set<String> links = uploaderFake.enviar(request.getImagens());
		System.out.println(links);
		produto.associaImagens(links);
		manager.merge(produto);
		return produto.toString();
	}
}
