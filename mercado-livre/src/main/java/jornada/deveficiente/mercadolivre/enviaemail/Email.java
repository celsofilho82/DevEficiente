package jornada.deveficiente.mercadolivre.enviaemail;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jornada.deveficiente.mercadolivre.adicionapergunta.Pergunta;

@Service
public class Email {

	@Autowired
	private FakeMailer mailer;

	public void novaPergunta(@NotNull @Valid Pergunta pergunta) {
		String clienteInteressado = pergunta.getUsuario().getLogin();
		String emailVendedor = pergunta.getProduto().getUsuario().getLogin();
		mailer.send("<html>...</html>", "Seu produto " + pergunta.getProduto().getNome() + " tem uma nova pergunta",
				clienteInteressado, emailVendedor, "novapergunta@nossomercadolivre.com");
	}

}
