package jornada.deveficiente.mercadolivre.cadastroproduto;

import java.util.Optional;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;
import jornada.deveficiente.mercadolivre.cadastrousuario.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class CadastroProdutosController {

	@Autowired
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private UploaderFake uploaderFake;

	@PostMapping
	@Transactional
	public ResponseEntity<Produto> create(@RequestBody @Valid NovoProdutoRequest request) {
		Optional<Usuario> usuario = usuarioRepository.findByLogin("celsoribeiro@email.com");
		Produto produto = request.toModel(manager, usuario.get());
		manager.persist(produto);
		return ResponseEntity.ok(produto);
	}

	@PostMapping(path = "/{id}/imagens")
	@Transactional
	public void adicionaImagens(@PathVariable("id") Long id, @Valid NovasImagensRequest request) {
		/**
		 * Fluxo da implementação 1) Enviar as imagens para o repositório 2) Obter os
		 * links dessas imagens 3) Associar esses links com o produto 4) Instanciar o
		 * produto 5) Atualiar o produto com os links para as imagens
		 */
		Optional<Usuario> dono = usuarioRepository.findByLogin("celsoribeiro@email.com");
		Produto produto = manager.find(Produto.class, id);

		if (!produto.pertenceAoUsuario(dono.get())) {
			throw new ResponseStatusException(HttpStatus.FORBIDDEN);
		}

		Set<String> links = uploaderFake.envia(request.getImagens());
		produto.associaImagens(links);

		manager.merge(produto);
	}

}
