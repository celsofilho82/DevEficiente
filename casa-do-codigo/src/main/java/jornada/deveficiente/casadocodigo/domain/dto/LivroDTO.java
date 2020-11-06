package jornada.deveficiente.casadocodigo.domain.dto;

import java.util.List;
import java.util.stream.Collectors;

import jornada.deveficiente.casadocodigo.domain.model.Livro;

public class LivroDTO {

	private Long id;
	private String titulo;
	
	public LivroDTO(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
	}

	public Long getId() {
		return id;
	}

	public String getTitulo() {
		return titulo;
	}
	
	public static List<LivroDTO> convert(List<Livro> livros){
		return livros.stream().map(LivroDTO::new).collect(Collectors.toList());
	}
	
}
