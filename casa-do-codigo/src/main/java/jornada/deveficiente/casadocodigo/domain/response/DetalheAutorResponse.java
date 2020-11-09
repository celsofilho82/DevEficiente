package jornada.deveficiente.casadocodigo.domain.response;

import jornada.deveficiente.casadocodigo.domain.model.Autor;

public class DetalheAutorResponse {

	private String nome;
	private String descricao;

	public DetalheAutorResponse(Autor autor) {
		nome = autor.getNome();
		descricao = autor.getDescricao();
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
