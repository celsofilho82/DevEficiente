package jornada.deveficiente.casadocodigo.domain.response;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

import jornada.deveficiente.casadocodigo.domain.model.Livro;

public class DetalheLivroResponse {

	private DetalheCategoriaResponse categoria;

	private DetalheAutorResponse autor;

	private String titulo;
	private String resumo;
	private String sumario;
	private BigDecimal preco;
	private int numeroPaginas;
	private String isbn;
	private String dataPublicacao;

	public DetalheLivroResponse(Livro livro) {

		this.categoria = new DetalheCategoriaResponse(livro.getIdCategoria());
		this.autor = new DetalheAutorResponse(livro.getIdAutor());
		this.titulo = livro.getTitulo();
		this.resumo = livro.getResumo();
		this.sumario = livro.getSumario();
		this.preco = livro.getPreco();
		this.numeroPaginas = livro.getNumeroPaginas();
		this.isbn = livro.getIsbn();
		this.dataPublicacao = livro.getDataPublicacao().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
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

	public String getDataPublicacao() {
		return dataPublicacao;
	}

	public DetalheAutorResponse getAutor() {
		return autor;
	}

	public DetalheCategoriaResponse getCategoria() {
		return categoria;
	}

}
