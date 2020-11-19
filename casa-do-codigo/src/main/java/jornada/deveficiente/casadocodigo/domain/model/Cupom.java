package jornada.deveficiente.casadocodigo.domain.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
public class Cupom {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank String codigo;
	private @NotNull @Positive @Max(value = 100) int percentualDesconto;
	private @NotNull @Future LocalDate validade;

	public Cupom(@NotBlank String codigo, @NotNull @Positive @Max(value = 100) int percentualDesconto,
			@NotBlank @Future LocalDate validade) {
		this.codigo = codigo;
		this.percentualDesconto = percentualDesconto;
		this.validade = validade;
	}

	public String getCodigo() {
		return codigo;
	}

	public int getPercentualDesconto() {
		return percentualDesconto;
	}

	public LocalDate getValidade() {
		return validade;
	}

}
