package jornada.deveficiente.mercadolivre.enviaemail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public interface Mailer {

	// Criado uma interface chamada Mailer e nesse interface definimos o método
	// Send que recebe algum dos parâmetros abaixo:.

	/**
	 * 
	 * @param body     corpo do email
	 * @param subject  assunto do email
	 * @param nameFrom nome do cliente
	 * @param to       email do vendedor
	 * @param from     dominio de envio do email
	 */

	void send(@NotBlank String body, @NotBlank String subject, @NotBlank String nameFrom, @NotBlank @Email String to,
			@NotBlank @Email String from);

}
