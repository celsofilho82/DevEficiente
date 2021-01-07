package jornada.deveficiente.mercadolivre.cadastroproduto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

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

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	public NovoProdutoRequest(@NotBlank String nome, @NotBlank String descricao, @NotBlank @Positive BigDecimal valor,
			@NotNull @Positive int quantidade, Long idCategoria) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.idCategoria = idCategoria;
	}

	public Produto toModel(EntityManager manager, Usuario usuario) {
		Categoria categoria = manager.find(Categoria.class, this.idCategoria);
		return new Produto(this.nome, this.descricao, this.valor, this.quantidade, this.dataCadastro, categoria,
				usuario);
	}
}
