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

import jornada.deveficiente.casadocodigo.domain.model.Cupom;
import jornada.deveficiente.casadocodigo.domain.request.NovoCupomRequest;

@RestController
@RequestMapping("/cupons")
public class CupomDescontoController {

	@Autowired
	private EntityManager manager;

	@PostMapping
	@Transactional
	public ResponseEntity<Cupom> create(@RequestBody @Valid NovoCupomRequest request) {
		Cupom cupom = request.toModel();
		manager.persist(cupom);
		return ResponseEntity.ok(cupom);
	}

}
