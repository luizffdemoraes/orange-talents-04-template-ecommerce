package br.com.zupacademy.luiz.mercadolivre.finalizacompra;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraRequest {
	
	@Positive
	private int quantidade;
	@NotNull
	private Long idProduto;
	@NotNull
	private GatewayPagamento gateway;
	
	@Deprecated
	public CompraRequest() {
	}

	public CompraRequest(@Positive int quantidade, @NotNull Long idProduto, @NotNull GatewayPagamento gateway) {
		this.quantidade = quantidade;
		this.idProduto = idProduto;
		this.gateway = gateway;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public GatewayPagamento getGateway() {
		return gateway;
	}
	
	

	
}
