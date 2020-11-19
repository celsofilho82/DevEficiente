package jornada.deveficiente.casadocodigo.domain.request;

import java.time.LocalDate;

import javax.validation.constraints.Future;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import jornada.deveficiente.casadocodigo.domain.model.Cupom;
import jornada.deveficiente.casadocodigo.validation.UniqueValue;

public class NovoCupomRequest {

	@NotBlank
	@UniqueValue(domainClass = Cupom.class, fieldName = "codigo")
	private String codigo;

	@NotNull
	@Positive
	@Max(value = 100)
	private int percentualDesconto;

	@NotNull
	@Future
	@JsonFormat(pattern = "dd/MM/yyyy", shape = Shape.STRING)
	private LocalDate validade;

	public NovoCupomRequest(@NotBlank String codigo, @NotNull @Positive @Max(value = 100) int percentualDesconto) {
		super();
		this.codigo = codigo;
		this.percentualDesconto = percentualDesconto;
	}

	public void setValidade(LocalDate validade) {
		this.validade = validade;
	}

	public Cupom toModel() {
		return new Cupom(this.codigo, this.percentualDesconto, this.validade);
	}

}
