package jornada.deveficiente.mercadolivre.cadastrousuario;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class CadastroUsuarioController {

	@Autowired
	private EntityManager manager;

	@PostMapping
	@Transactional
	public ResponseEntity<Usuario> create(@RequestBody @Valid NovoUsuarioRequest request) {
		Usuario usuario = request.toModel();
		manager.persist(usuario);
		return ResponseEntity.ok(usuario);
	}

}
