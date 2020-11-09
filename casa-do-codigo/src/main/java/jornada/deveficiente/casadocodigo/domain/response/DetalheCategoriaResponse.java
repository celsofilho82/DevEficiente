package jornada.deveficiente.casadocodigo.domain.response;

import jornada.deveficiente.casadocodigo.domain.model.Categoria;

public class DetalheCategoriaResponse {

	private String nome;

	public DetalheCategoriaResponse(Categoria categoria) {
		nome = categoria.getNome();
	}

	public String getNome() {
		return nome;
	}
	
}
