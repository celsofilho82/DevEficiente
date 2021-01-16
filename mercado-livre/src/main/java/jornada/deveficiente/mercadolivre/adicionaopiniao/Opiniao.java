package jornada.deveficiente.mercadolivre.adicionaopiniao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import jornada.deveficiente.mercadolivre.cadastroproduto.Produto;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;

@Entity
public class Opiniao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String descricao;
	private @Positive @Min(1) @Max(5) int nota;
	
	@Valid
	@NotNull
	@ManyToOne
	private Produto produto;
	
	@Valid
	@NotNull
	@ManyToOne
	private Usuario usuario;

	public Opiniao(@NotBlank String titulo, @NotBlank @Size(max = 500) String descricao,
			@Positive @Min(1) @Max(5) int nota, Produto produto, Usuario usuario) {
				this.titulo = titulo;
				this.descricao = descricao;
				this.nota = nota;
				this.produto = produto;
				this.usuario = usuario;
	}

}
