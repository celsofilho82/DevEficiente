package jornada.deveficiente.mercadolivre.adicionapergunta;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jornada.deveficiente.mercadolivre.cadastroproduto.Produto;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank String titulo;
	private @NotNull LocalDateTime dataCriacao;

	@Valid
	@NotNull
	@ManyToOne
	private Produto produto;

	@Valid
	@NotNull
	@ManyToOne
	private Usuario usuario;

	public Pergunta(@NotBlank String titulo, @NotNull LocalDateTime dataCriacao, Produto produto, Usuario usuario) {
		this.titulo = titulo;
		this.dataCriacao = dataCriacao;
		this.produto = produto;
		this.usuario = usuario;
	}

}
