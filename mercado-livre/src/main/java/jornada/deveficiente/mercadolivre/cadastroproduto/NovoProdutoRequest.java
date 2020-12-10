package jornada.deveficiente.mercadolivre.cadastroproduto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import jornada.deveficiente.mercadolivre.cadastrocategoria.Categoria;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;
import jornada.deveficiente.mercadolivre.compartilhado.ExistsId;

public class NovoProdutoRequest {

	@NotBlank
	private String nome;

	@NotBlank
	@Positive
	private BigDecimal valor;

	@NotNull
	@Positive
	private int quantidade;

	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private Long idCategoria;

	private LocalDate dataCadastro = LocalDate.now();

	public NovoProdutoRequest(@NotBlank String nome, @NotBlank @Positive BigDecimal valor,
			@NotNull @Positive int quantidade, Long idCategoria) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.idCategoria = idCategoria;
	}

	public Produto toModel(EntityManager manager, Usuario usuario) {
		Categoria categoria = manager.find(Categoria.class, this.idCategoria);
		return new Produto(this.nome, this.valor, this.quantidade, this.dataCadastro, categoria, usuario);
	}
}
