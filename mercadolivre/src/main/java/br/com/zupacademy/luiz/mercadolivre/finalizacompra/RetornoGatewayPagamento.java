package br.com.zupacademy.luiz.mercadolivre.finalizacompra;

public interface RetornoGatewayPagamento {
	
	/**
	 * 
	 * @param compra
	 * @return uma nova transacao em função do gateway específico
	 */
	Transacao toTransacao(Compra compra);

}
