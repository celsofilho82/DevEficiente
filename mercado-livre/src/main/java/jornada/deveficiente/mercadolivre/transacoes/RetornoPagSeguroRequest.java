package jornada.deveficiente.mercadolivre.transacoes;

import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jornada.deveficiente.mercadolivre.fechacompra.Compra;
import jornada.deveficiente.mercadolivre.fechacompra.RetornoGatewayPagamento;

public class RetornoPagSeguroRequest implements RetornoGatewayPagamento {

	@NotBlank
	private String idTransacao;

	@Enumerated
	@NotNull
	private StatusRetornoPagSeguro status;

	public RetornoPagSeguroRequest(@NotBlank String idTransacao, @NotNull StatusRetornoPagSeguro status) {
		this.idTransacao = idTransacao;
		this.status = status;
	}

	public Transacao toTransacao(Compra compra) {
		return new Transacao(this.status.normaliza(), this.idTransacao, compra);
	}

}
