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
	private int percentualDescontoMomento;

	@NotNull
	@Future
	private LocalDate validadeMomento;

	@Deprecated
	public CupomAplicado() {
	}

	public CupomAplicado(Cupom cupom) {
		this.cupom = cupom;
		this.percentualDescontoMomento = cupom.getPercentualDesconto();
		this.validadeMomento = cupom.getValidade();
	}

	public int getPercentualDescontoMomento() {
		return percentualDescontoMomento;
	}

	public LocalDate getValidadeMomento() {
		return validadeMomento;
	}

}
