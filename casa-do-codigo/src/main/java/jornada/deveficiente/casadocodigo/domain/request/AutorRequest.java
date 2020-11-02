package jornada.deveficiente.casadocodigo.domain.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import jornada.deveficiente.casadocodigo.domain.model.Autor;

/*
 * Usando Form Value Objects para separar os dados que chegam da request do
 * objeto do dominio, fazer as validações e depois converter os parâmetros da
 * requisição em um objeto do dominio
 */

public class AutorRequest {
	@NotBlank
	private String nome;

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Size(max = 400)
	private String descricao;

	public AutorRequest(@NotBlank String nome, @NotBlank @Email String email,
			@NotBlank @Size(max = 400) String descricao) {
		super();
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	public Autor toModel() {
		return new Autor(this.nome, this.email, this.descricao);
	}

}
