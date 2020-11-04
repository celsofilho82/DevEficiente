package jornada.deveficiente.casadocodigo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jornada.deveficiente.casadocodigo.domain.model.Categoria;
import jornada.deveficiente.casadocodigo.domain.request.CategoriaRequest;

@RestController
public class CategoriasController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/categorias")
	@Transactional
	public ResponseEntity<Categoria> create(@RequestBody @Valid CategoriaRequest request){
		Categoria novaCategoria = new Categoria(request.getNome());
		manager.persist(novaCategoria);
		return ResponseEntity.ok(novaCategoria);
	}
}
