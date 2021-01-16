package jornada.deveficiente.mercadolivre.adicionapergunta;

import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jornada.deveficiente.mercadolivre.cadastroproduto.Produto;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;

public class NovaPerguntaRequest {

	@NotBlank
	private String titulo;

	@NotNull
	private LocalDateTime dataCriacao = LocalDateTime.now();

	public NovaPerguntaRequest(@NotBlank String titulo) {
		this.setTitulo(titulo);
	}

	public Pergunta toModel(Produto produto, Usuario usuario) {
		return new Pergunta(this.titulo, this.dataCriacao, produto, usuario);
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
