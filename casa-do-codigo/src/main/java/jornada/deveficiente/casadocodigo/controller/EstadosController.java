package jornada.deveficiente.casadocodigo.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jornada.deveficiente.casadocodigo.domain.model.Estado;
import jornada.deveficiente.casadocodigo.domain.request.EstadoRequest;

@RestController
@RequestMapping("/estados")
public class EstadosController {

	@Autowired
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Estado> create(@RequestBody @Valid EstadoRequest request){
		Estado estado = request.toModel(manager);
		manager.persist(estado);
		return ResponseEntity.ok(estado);
	}
}
