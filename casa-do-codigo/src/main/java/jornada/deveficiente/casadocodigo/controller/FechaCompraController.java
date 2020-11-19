package jornada.deveficiente.casadocodigo.controller;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jornada.deveficiente.casadocodigo.domain.model.Compra;
import jornada.deveficiente.casadocodigo.domain.repository.CupomRepository;
import jornada.deveficiente.casadocodigo.domain.request.NovaCompraRequest;
import jornada.deveficiente.casadocodigo.validation.CupomValidoValidator;
import jornada.deveficiente.casadocodigo.validation.EstadoPertencePaisValidator;
import jornada.deveficiente.casadocodigo.validation.VerificaDocumentoCpfOuCnpjValidator;

@RestController
@RequestMapping("/compras")
public class FechaCompraController {

	@Autowired
	private EntityManager manager;
	
	@Autowired
	private EstadoPertencePaisValidator estadoPertencePaisValidator;
	
	@Autowired
	private CupomRepository cupomRepository;
	
	@Autowired
	private CupomValidoValidator cupomValidoValidator;

	@InitBinder
	public void init(WebDataBinder binder) {
		binder.addValidators(new VerificaDocumentoCpfOuCnpjValidator(),estadoPertencePaisValidator,cupomValidoValidator);
	}

	@PostMapping
	@Transactional
	public ResponseEntity<Compra> create(@RequestBody @Valid NovaCompraRequest request) {
		
		Compra novaCompra = request.toModel(manager, cupomRepository);
		manager.persist(novaCompra);
		return ResponseEntity.ok(novaCompra);
	}

}
