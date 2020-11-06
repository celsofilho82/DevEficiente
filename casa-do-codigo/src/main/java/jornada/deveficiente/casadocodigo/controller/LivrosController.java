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

import jornada.deveficiente.casadocodigo.domain.dto.LivroDTO;
import jornada.deveficiente.casadocodigo.domain.model.Livro;
import jornada.deveficiente.casadocodigo.domain.repository.LivroRepository;
import jornada.deveficiente.casadocodigo.domain.request.LivroRequest;

@RestController
public class LivrosController {

	@Autowired
	private EntityManager manager;
	
	@Autowired
	private LivroRepository livroRepository;

	@PostMapping("/livros")
	@Transactional
	public ResponseEntity<Livro> create(@RequestBody @Valid LivroRequest request) {
		Livro livro = request.toModel(manager);
		manager.persist(livro);
		return ResponseEntity.ok(livro);
	}

	@GetMapping("/livros")
	public List<LivroDTO> list() {
		List<Livro> livros = livroRepository.findAll();
		return LivroDTO.convert(livros);
	}
}
