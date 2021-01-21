package jornada.deveficiente.mercadolivre.fechacompra;

import jornada.deveficiente.mercadolivre.transacoes.Transacao;

public interface RetornoGatewayPagamento {

	/**
	 * 
	 * @param compra
	 * @return uma nova transação em função do gateway especifico
	 */
	Transacao toTransacao(Compra compra);
}
