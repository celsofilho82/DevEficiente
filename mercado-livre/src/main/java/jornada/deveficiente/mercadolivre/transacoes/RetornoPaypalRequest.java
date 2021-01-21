package jornada.deveficiente.mercadolivre.transacoes;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import jornada.deveficiente.mercadolivre.fechacompra.Compra;
import jornada.deveficiente.mercadolivre.fechacompra.RetornoGatewayPagamento;

public class RetornoPaypalRequest implements RetornoGatewayPagamento {

	@Min(0)
	@Max(1)
	private int status;

	@NotBlank
	private String idTransacao;

	public RetornoPaypalRequest(@Min(0) @Max(1) int status, @NotBlank String idTransacao) {
		this.status = status;
		this.idTransacao = idTransacao;
	}

	public Transacao toTransacao(Compra compra) {
		return new Transacao(this.status == 0 ? StatusTransacao.erro : StatusTransacao.sucesso, this.idTransacao,
				compra);
	}

}
