package jornada.deveficiente.casadocodigo.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import jornada.deveficiente.casadocodigo.domain.model.Autor;
import jornada.deveficiente.casadocodigo.domain.model.Categoria;
import jornada.deveficiente.casadocodigo.domain.model.Livro;

public class LivroDetalhesDTO {

	private Categoria idCategoria;

	private Autor idAutor;

	private String titulo;
	private String resumo;
	private String sumario;
	private BigDecimal preco;
	private int numeroPaginas;
	private String isbn;
	private LocalDate dataPublicacao;

	public LivroDetalhesDTO(Livro livro) {

		this.idCategoria = livro.getIdCategoria();
		this.idAutor = livro.getIdAutor();
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.preco = livro.getPreco();
		this.numeroPaginas = livro.getNumeroPaginas();
		this.isbn = livro.getIsbn();
		this.dataPublicacao = livro.getDataPublicacao();
	}

	public Categoria getIdCategoria() {
		return idCategoria;
	}

	public Autor getIdAutor() {
		return idAutor;
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

}
