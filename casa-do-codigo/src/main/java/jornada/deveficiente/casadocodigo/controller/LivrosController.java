package jornada.deveficiente.casadocodigo.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jornada.deveficiente.casadocodigo.domain.dto.LivroDTO;
import jornada.deveficiente.casadocodigo.domain.dto.LivroDetalhesDTO;
import jornada.deveficiente.casadocodigo.domain.model.Livro;
import jornada.deveficiente.casadocodigo.domain.repository.LivroRepository;
import jornada.deveficiente.casadocodigo.domain.request.LivroRequest;

@RestController
@RequestMapping("/livros")
public class LivrosController {

	@Autowired
	private EntityManager manager;

	@Autowired
	private LivroRepository livroRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<Livro> create(@RequestBody @Valid LivroRequest request) {
		Livro livro = request.toModel(manager);
		manager.persist(livro);
		return ResponseEntity.ok(livro);
	}

	@GetMapping
	public List<LivroDTO> list() {
		List<Livro> livros = livroRepository.findAll();
		return LivroDTO.convert(livros);
	}

	@GetMapping("/{id}")
	public ResponseEntity<LivroDetalhesDTO> details(@PathVariable Long id) {
		Optional<Livro> possivelLivro = livroRepository.findById(id);
		if (possivelLivro.isPresent()) {
			return ResponseEntity.ok(new LivroDetalhesDTO(possivelLivro.get()));
		}

		return ResponseEntity.notFound().build();
	}
}
