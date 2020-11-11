package jornada.deveficiente.casadocodigo.domain.request;

import javax.validation.constraints.NotBlank;

import jornada.deveficiente.casadocodigo.domain.model.Pais;
import jornada.deveficiente.casadocodigo.validation.UniqueValue;

public class PaisRequest {

	@NotBlank
	@UniqueValue(domainClass = Pais.class,fieldName = "nome")
	private String nome;

	public Pais toModel() {
		return new Pais(this.nome);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
