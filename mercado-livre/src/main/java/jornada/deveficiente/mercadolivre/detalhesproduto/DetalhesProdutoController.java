package jornada.deveficiente.mercadolivre.detalhesproduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jornada.deveficiente.mercadolivre.cadastroproduto.Produto;

@RestController
@RequestMapping("/produtos/{id}")
public class DetalhesProdutoController {

	@PersistenceContext
	private EntityManager manager;
	
	@GetMapping
	public DetalhesProdutoResponse detalhesProduto(@PathVariable("id") Long id) {
		Produto produto = manager.find(Produto.class, id);
		
		return new DetalhesProdutoResponse(produto);
	}
}
