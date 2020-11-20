package jornada.deveficiente.casadocodigo.domain.model;

import java.time.LocalDate;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Embeddable
public class CupomAplicado {

	@ManyToOne
	private Cupom cupom;

	@Positive
	@NotNull
	private int percentualDesconto;

	@NotNull
	@Future
	private LocalDate validade;

	@Deprecated
	public CupomAplicado() {
	}

	public CupomAplicado(Cupom cupom) {
		this.cupom = cupom;
		this.percentualDesconto = cupom.getPercentualDesconto();
		this.validade = cupom.getValidade();
	}

	public String getCupom() {
		return cupom.getCodigo();
	}

	public int getPercentualDescontoMomento() {
		return percentualDesconto;
	}

	public LocalDate getValidadeMomento() {
		return validade;
	}

}
