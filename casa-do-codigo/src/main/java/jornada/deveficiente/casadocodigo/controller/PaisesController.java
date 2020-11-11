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

import jornada.deveficiente.casadocodigo.domain.model.Pais;
import jornada.deveficiente.casadocodigo.domain.request.PaisRequest;

@RestController
@RequestMapping("/paises")
public class PaisesController {
	
	@Autowired
	private EntityManager manager;
	
	@PostMapping
	@Transactional
	public ResponseEntity<Pais> create(@RequestBody @Valid PaisRequest request){
		Pais pais = request.toModel();
		manager.persist(pais);
		return ResponseEntity.ok(pais);
	}
}
