package jornada.deveficiente.mercadolivre.cadastroproduto;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;
import jornada.deveficiente.mercadolivre.cadastrousuario.UsuarioRepository;

@RestController
@RequestMapping("/produtos")
public class CadastroProdutosController {

	@Autowired
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<Produto> create(@RequestBody @Valid NovoProdutoRequest request) {
		Optional<Usuario> usuario = usuarioRepository.findByLogin("celsoribeiro@email.com");
		Produto produto = request.toModel(manager, usuario.get());
		manager.persist(produto);
		return ResponseEntity.ok(produto);
	}

}
