package jornada.deveficiente.casadocodigo.domain.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import jornada.deveficiente.casadocodigo.domain.model.Estado;
import jornada.deveficiente.casadocodigo.domain.model.Pais;
import jornada.deveficiente.casadocodigo.validation.UniqueValue;

public class EstadoRequest {

	@NotBlank
	@UniqueValue(domainClass = Estado.class,fieldName = "nome")
	private String nome;
	
	@NotNull
	private Long idPais;

	public EstadoRequest(@NotBlank String nome, @NotNull Long idPais) {
		super();
		this.nome = nome;
		this.idPais = idPais;
	}
	
	public Estado toModel(EntityManager manager) {
		Pais pais = manager.find(Pais.class, idPais);
		
		Assert.state(pais != null, "O País informado não está cadastrado");
		
		return new Estado(this.nome, pais);
	}
	
}
