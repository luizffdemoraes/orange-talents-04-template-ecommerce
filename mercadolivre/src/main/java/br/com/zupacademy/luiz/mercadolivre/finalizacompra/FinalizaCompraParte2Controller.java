package br.com.zupacademy.luiz.mercadolivre.finalizacompra;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class FinalizaCompraParte2Controller {
	
	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	public String processamentoPagSeguro(@PathVariable("id") Long idCompra, @Valid RetornoPagseguroRequest request) {
		return processa(idCompra, request);
	}
	
	@PostMapping(value = "/retorno-paypal/{id}")
	@Transactional
	public String processamentoPaypal(@PathVariable("id") Long idCompra, @Valid RetornoPaypalRequest request) {
		return processa(idCompra, request);
	}
	

	private String processa(Long idCompra,RetornoGatewayPagamento retornoGatewayPagamento) {
		Compra compra = manager.find(Compra.class, idCompra);
		compra.adicionaTransacao(retornoGatewayPagamento);		
		manager.merge(compra);
		
		return compra.toString();		
	}

}