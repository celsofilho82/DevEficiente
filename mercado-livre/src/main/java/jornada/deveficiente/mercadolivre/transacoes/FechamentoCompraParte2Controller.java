package jornada.deveficiente.mercadolivre.transacoes;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jornada.deveficiente.mercadolivre.fechacompra.Compra;

@RestController
public class FechamentoCompraParte2Controller {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	public void processamentoPagSeguro(@PathVariable("id") Long id,
			@RequestBody @Valid RetornoPagSeguroRequest request) {

		Compra compra = manager.find(Compra.class, id);
		compra.adicionaTransacao(request);
		manager.merge(compra);
	}
}
