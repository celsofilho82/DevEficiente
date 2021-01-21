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
import jornada.deveficiente.mercadolivre.fechacompra.RetornoGatewayPagamento;

@RestController
public class FechamentoCompraParte2Controller {

	@PersistenceContext
	private EntityManager manager;

	@PostMapping(value = "/retorno-pagseguro/{id}")
	@Transactional
	public void processamentoPagSeguro(@PathVariable("id") Long id,
			@RequestBody @Valid RetornoPagSeguroRequest request) {

		processaPagamento(id, request);
	}

	@PostMapping(value = "/retorno-paypal/{id}")
	@Transactional
	public void processamentoPaypal(@PathVariable("id") Long id, @RequestBody @Valid RetornoPaypalRequest request) {

		processaPagamento(id, request);
	}

	private void processaPagamento(Long idComrpa, RetornoGatewayPagamento retornoGatewayPagamento) {
		Compra compra = manager.find(Compra.class, idComrpa);
		compra.adicionaTransacao(retornoGatewayPagamento);
		manager.merge(compra);
	}
}
