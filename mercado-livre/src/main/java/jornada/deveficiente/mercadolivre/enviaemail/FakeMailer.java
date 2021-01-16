package jornada.deveficiente.mercadolivre.enviaemail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FakeMailer implements Mailer {

	// Para efeitos de teste criado uma implementação da interface Mailer que
	// imprime os dados no console somente como forma de validar os dados

	@Override
	public void send(@NotBlank String body, @NotBlank String subject, @NotBlank String nameFrom,
			@NotBlank @Email String to, @NotBlank @Email String from) {

		System.out.println(body);
		System.out.println(subject);
		System.out.println(nameFrom);
		System.out.println(to);
		System.out.println(from);

	}

}
