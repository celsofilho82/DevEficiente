package jornada.deveficiente.mercadolivre.adicionapergunta;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jornada.deveficiente.mercadolivre.cadastroproduto.Produto;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;
import jornada.deveficiente.mercadolivre.cadastrousuario.UsuarioRepository;
import jornada.deveficiente.mercadolivre.enviaemail.Email;

@RestController
@RequestMapping("/produtos/{id}/perguntas")
public class NovaPerguntaController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private Email emails;

	@PostMapping
	@Transactional
	public void adicionaPergunta(@PathVariable("id") Long id, @RequestBody @Valid NovaPerguntaRequest request) {
		Optional<Usuario> consumidor = usuarioRepository.findByLogin("celsoribeiro@email.com");
		Produto produto = manager.find(Produto.class, id);

		if (!produto.pertenceAoUsuario(consumidor.get())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}

		Pergunta pergunta = request.toModel(produto, consumidor.get());
		manager.persist(pergunta);
		emails.novaPergunta(pergunta);
	}
}
