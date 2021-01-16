package jornada.deveficiente.mercadolivre.adicionaopiniao;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import jornada.deveficiente.mercadolivre.cadastroproduto.Produto;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;

public class NovaOpiniaoRequest {

	@Positive
	@Min(value = 1)
	@Max(value = 5)
	private int nota;

	@NotBlank
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	public NovaOpiniaoRequest(@Positive @Min(1) @Max(5) int nota, @NotBlank String titulo,
			@NotBlank @Max(500) String descricao) {
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Opiniao toModel(Produto produto, Usuario usuario) {
		return new Opiniao(this.titulo, this.descricao, this.nota, produto, usuario);
	}


}
