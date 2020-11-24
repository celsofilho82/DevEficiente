package jornada.deveficiente.mercadolivre.cadastrousuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank @Email String login;
	private @NotBlank @Length(min = 6) String senha;
	private LocalDateTime dataCadastro;

	@Deprecated
	public Usuario() {
	}

	public Usuario(@NotBlank @Email String login, @NotBlank @Length(min = 6) String senha, LocalDateTime dataCadastro) {
		this.login = login;
		this.senha = new BCryptPasswordEncoder().encode(senha);
		this.dataCadastro = dataCadastro;
	}

	public String getLogin() {
		return login;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}

}
