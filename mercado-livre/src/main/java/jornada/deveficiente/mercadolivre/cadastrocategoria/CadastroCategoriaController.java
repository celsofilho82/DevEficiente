package jornada.deveficiente.mercadolivre.cadastrocategoria;

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
@RequestMapping("/categorias")
public class CadastroCategoriaController {

	@Autowired
	private EntityManager manager;

	@PostMapping
	@Transactional
	public ResponseEntity<Categoria> create(@RequestBody @Valid NovaCategoriaRequest request) {
		Categoria categoria = request.toModel(manager);
		manager.persist(categoria);
		return ResponseEntity.ok(categoria);
	}

}
