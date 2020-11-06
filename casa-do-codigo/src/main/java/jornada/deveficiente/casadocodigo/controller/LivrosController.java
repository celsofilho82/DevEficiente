package jornada.deveficiente.casadocodigo.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jornada.deveficiente.casadocodigo.domain.model.Livro;
import jornada.deveficiente.casadocodigo.domain.request.LivroRequest;

@RestController
public class LivrosController {

	@Autowired
	private EntityManager manager;

	@PostMapping("/livros")
	@Transactional
	public ResponseEntity<Livro> create(@RequestBody @Valid LivroRequest request) {
		Livro livro = request.toModel(manager);
		manager.persist(livro);
		return ResponseEntity.ok(livro);
	}

	@GetMapping("/livros")
	public List<?> list() {
		return manager.createQuery("SELECT p.id, p.titulo FROM Livro p").getResultList();
	}
}
