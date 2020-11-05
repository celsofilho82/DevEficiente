package jornada.deveficiente.casadocodigo.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Livro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	@Valid
	private Categoria idCategoria;

	@ManyToOne
	@NotNull
	@Valid
	private Autor idAutor;

	private @NotBlank String titulo;
	private @NotBlank @Size(max = 500) String resumo;
	private @NotBlank String sumario;
	private @NotNull @Min(20) BigDecimal preco;
	private @NotNull @Min(100) int numeroPaginas;
	private @NotBlank String isbn;
	private @Future @NotNull LocalDate dataPublicacao;

	public Livro(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo, @NotBlank String sumario,
			@NotNull @Min(20) BigDecimal preco, @NotNull @Min(100) int numeroPaginas, @NotBlank String isbn,
			@Future @NotNull LocalDate dataPublicacao, @NotNull @Valid Autor autor,
			@NotNull @Valid Categoria categoria) {
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.preco = preco;
		this.numeroPaginas = numeroPaginas;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.idCategoria = categoria;
		this.idAutor = autor;
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getResumo() {
		return resumo;
	}

	public String getSumario() {
		return sumario;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public int getNumeroPaginas() {
		return numeroPaginas;
	}

	public String getIsbn() {
		return isbn;
	}

	public LocalDate getDataPublicacao() {
		return dataPublicacao;
	}

	public Categoria getIdCategoria() {
		return idCategoria;
	}

	public Autor getIdAutor() {
		return idAutor;
	}

}
