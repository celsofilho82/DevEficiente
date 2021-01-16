package jornada.deveficiente.mercadolivre.adicionaopiniao;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jornada.deveficiente.mercadolivre.cadastroproduto.Produto;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;
import jornada.deveficiente.mercadolivre.cadastrousuario.UsuarioRepository;

@RestController
@RequestMapping("/produtos/{id}/opinioes")
public class NovaOpiniaoController {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	@Transactional
	public void adicionaOpiniao(@PathVariable("id") Long id, @Valid NovaOpiniaoRequest request) {

		Optional<Usuario> consumidor = usuarioRepository.findByLogin("celsoribeiro@email.com");
		Produto produto = manager.find(Produto.class, id);

		if (!produto.pertenceAoUsuario(consumidor.get())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}

		Opiniao novaOpiniao = request.toModel(produto, consumidor.get());
		manager.persist(novaOpiniao);
	}
}
