package jornada.deveficiente.casadocodigo.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jornada.deveficiente.casadocodigo.domain.model.Autor;
import jornada.deveficiente.casadocodigo.domain.request.AutorRequest;
import jornada.deveficiente.casadocodigo.validation.EmailAutorDeveSerUnicoValidador;

/*
 * Controller 100% Coeso
 * Controllers que utilizam todos os atributos.
 * Carga Intriseca: 3 pontos
 */

@RestController
public class AutoresController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	//1
	private EmailAutorDeveSerUnicoValidador emailAutorDeveSerUnicoValidador;

	/*
	 * No primeiro request para este controller o código dentro do método init será
	 * executado. Aqui podemos inserir validações.  
	 */
	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(emailAutorDeveSerUnicoValidador);
	}

	@PostMapping(value = "/autores")
	@Transactional
	//2
	public ResponseEntity<Autor> create(@RequestBody @Valid AutorRequest request) {
		Autor autor = request.toModel();
		manager.persist(autor);
		return ResponseEntity.ok(autor);
	}
}
