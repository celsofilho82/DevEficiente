package jornada.deveficiente.mercadolivre.cadastroproduto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;

import jornada.deveficiente.mercadolivre.cadastrocategoria.Categoria;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank String nome;
	private @NotBlank @Positive BigDecimal valor;
	private @NotNull @Positive int quantidade;
	private LocalDate dataCadastro;

	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;

	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	public Produto(@NotBlank String nome, @NotBlank @Length(max = 1000) String descricao,
			@NotBlank @Positive BigDecimal valor, @NotNull @Positive int quantidade, LocalDate dataCadastro,
			@NotNull @Valid Categoria categoria, Usuario usuario) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.dataCadastro = dataCadastro;
		this.categoria = categoria;
		this.usuario = usuario;

	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getDescricao() {
		return descricao;
	}

}
