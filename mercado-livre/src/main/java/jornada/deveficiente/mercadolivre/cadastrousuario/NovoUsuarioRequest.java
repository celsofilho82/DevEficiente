package jornada.deveficiente.mercadolivre.cadastrousuario;

import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class NovoUsuarioRequest {

	@NotBlank
	@Email
	private String login;

	@NotBlank
	@Length(min = 6)
	private String senha;

	private LocalDateTime dataCadastro = LocalDateTime.now();

	public NovoUsuarioRequest(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(this.getLogin(), this.senha, this.dataCadastro);
	}

	public String getLogin() {
		return login;
	}

}
