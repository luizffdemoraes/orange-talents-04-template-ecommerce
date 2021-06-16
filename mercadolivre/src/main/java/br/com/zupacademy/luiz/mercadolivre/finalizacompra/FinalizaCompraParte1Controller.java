package br.com.zupacademy.luiz.mercadolivre.finalizacompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.validation.BindException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zupacademy.luiz.mercadolivre.produto.Produto;
import br.com.zupacademy.luiz.mercadolivre.usuario.Usuario;

@RestController
public class FinalizaCompraParte1Controller {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/compras")
	@Transactional
	public String finalizaCompra(@RequestBody @Valid CompraRequest request, @AuthenticationPrincipal Usuario usuarioLogado, UriComponentsBuilder uriComponentsBuilder) throws BindException {

		Produto produtoASerComprado = manager.find(Produto.class, request.getIdProduto());
		
		int quantidade = request.getQuantidade();
		boolean abateu = produtoASerComprado.abataEstoque(request.getQuantidade());
		
		if(abateu) {
			Usuario comprador = usuarioLogado;
			GatewayPagamento gateway = request.getGateway();
			Compra novaCompra = new Compra(produtoASerComprado, quantidade, comprador, request.getGateway());
			manager.persist(novaCompra);
			
			if (gateway.equals(GatewayPagamento.pagseguro)) {
				String urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
						.buildAndExpand(novaCompra.getId()).toString();

				return "pagseguro.com/" + novaCompra.getId()
						+ "?redirectUrl="+urlRetornoPagseguro;
			}
			//1
			else {
				String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
						.buildAndExpand(novaCompra.getId()).toString();

				return "paypal.com/" + novaCompra.getId()
						+ "?redirectUrl="+urlRetornoPaypal;
			}
		}
		//Exception para controle de fluxo
		BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
		problemaComEstoque.reject(null, "Não foi possível realizar a compra por conta do estoque.");
		throw problemaComEstoque;
		
		

	}

}
