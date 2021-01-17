package jornada.deveficiente.mercadolivre.fechacompra;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jornada.deveficiente.mercadolivre.cadastroproduto.Produto;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;
import jornada.deveficiente.mercadolivre.cadastrousuario.UsuarioRepository;

@RestController
@RequestMapping("/compras")
public class FechaCompraParte1Controller {

	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@PostMapping
	@Transactional
	public String comprar(@RequestBody @Valid NovaCompraRequest request) throws BindException {
		Produto produto = manager.find(Produto.class, request.getIdProduto());
		boolean abateuEstoque = produto.abateEstoque(request.getQuantidade());
		if (abateuEstoque) {
			Optional<Usuario> comprador = usuarioRepository.findByLogin("celsoribeiro@email.com");
			Compra novaCompra = new Compra(produto, request.getQuantidade(), comprador.get(), request.getGateway());
			manager.persist(novaCompra);

			if (request.getGateway().equals(GatewayPagamento.Pagseguro)) {
				return "pagseguro.com?returnId={idGeradoDaCompra}&redirectUrl={urlRetornoAppPosPagamento}";
			} else {
				return "paypal.com/{idGeradoDaCompra}?redirectUrl={urlRetornoAppPosPagamento}";
			}

		}

		BindException problemaComEstoque = new BindException(request, "novaCompraRequest");
		problemaComEstoque.reject(null, "Produto sem estoque!!!");
		throw problemaComEstoque;
	}
}
