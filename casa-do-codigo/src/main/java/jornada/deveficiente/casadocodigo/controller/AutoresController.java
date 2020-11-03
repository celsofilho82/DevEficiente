package jornada.deveficiente.casadocodigo.api.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jornada.deveficiente.casadocodigo.domain.model.Autor;
import jornada.deveficiente.casadocodigo.domain.request.AutorRequest;

/*
 * Controller 100% Coeso
 * Controllers que utilizam todos os atributos.
 * Carga Intriseca: 2 pontos
 */

@RestController
public class AutoresController {

	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/autores")
	@Transactional
	public ResponseEntity<Autor> create(@RequestBody @Valid AutorRequest request) {
		Autor autor = request.toModel();
		manager.persist(autor);
		return ResponseEntity.ok(autor);
	}
}

