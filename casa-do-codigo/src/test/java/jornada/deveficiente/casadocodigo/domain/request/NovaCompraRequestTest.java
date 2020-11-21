package jornada.deveficiente.casadocodigo.domain.request;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mockito;

import jornada.deveficiente.casadocodigo.domain.model.Autor;
import jornada.deveficiente.casadocodigo.domain.model.Categoria;
import jornada.deveficiente.casadocodigo.domain.model.Compra;
import jornada.deveficiente.casadocodigo.domain.model.Cupom;
import jornada.deveficiente.casadocodigo.domain.model.Estado;
import jornada.deveficiente.casadocodigo.domain.model.Livro;
import jornada.deveficiente.casadocodigo.domain.model.Pais;
import jornada.deveficiente.casadocodigo.domain.repository.CupomRepository;

public class NovaCompraRequestTest {

	private EntityManager manager = Mockito.mock(EntityManager.class);
	private CupomRepository cupomRepository = Mockito.mock(CupomRepository.class);

	private List<NovoPedidoItemRequest> itens = List.of(new NovoPedidoItemRequest(1l, 5));
	private NovoPedidoRequest pedido = new NovoPedidoRequest(new BigDecimal("50"), itens);

	private Pais pais = new Pais("pais");
	private Autor autor = new Autor("nome", "email@email.com", "descricao");
	private Categoria categoria = new Categoria("categoria");
	private Livro livro = new Livro("titulo", "resunmo", "sumario", BigDecimal.TEN, 100, "97834985782",
			LocalDate.of(2000, 10, 10), autor, categoria);

	{
		Mockito.when(manager.find(Pais.class, 1l)).thenReturn(pais);

		Mockito.when(manager.find(Livro.class, 1l)).thenReturn(livro);

		Mockito.when(manager.find(Estado.class, 1l)).thenReturn(new Estado("estado", pais));

		Mockito.when(cupomRepository.findByCodigo("codigo-cupom"))
				.thenReturn(new Cupom("codigo-cupom", 10, LocalDate.now().plusDays(1l)));
	}

	private NovaCompraRequest request = new NovaCompraRequest("email@email.com", "Alberto", "Souza", "11111111",
			"endereco", "complemento", "salvador", 1l, 1l, "999999999", "400000", pedido);

	@Test
	@DisplayName("cria compra com estado e cupom")
	void teste1() throws Exception {

		request.setCodigoCupom("codigo-cupom");
		Compra novaCompra = request.toModel(manager, cupomRepository);

		Assertions.assertNotNull(novaCompra);
		Mockito.verify(manager).find(Estado.class, 1l);
		Mockito.verify(cupomRepository).findByCodigo("codigo-cupom");

	}

	@Test
	@DisplayName("cria compra sem estado e com cupom")
	void teste2() throws Exception {
		request.setCodigoCupom("codigo-cupom");
		Compra novaCompra = request.toModel(manager, cupomRepository);

		Assertions.assertNotNull(novaCompra);

		Mockito.verify(manager, Mockito.never()).find(Mockito.eq(Estado.class), Mockito.anyLong());
		Mockito.verify(cupomRepository).findByCodigo("codigo-cupom");

	}

	@Test
	@DisplayName("cria compra sem estado e sem cupom")
	void teste3() throws Exception {
		Compra novaCompra = request.toModel(manager, cupomRepository);

		Assertions.assertNotNull(novaCompra);

		Mockito.verify(manager, Mockito.never()).find(Mockito.eq(Estado.class), Mockito.anyLong());
		Mockito.verify(cupomRepository, Mockito.never()).findByCodigo(Mockito.anyString());

	}

	@ParameterizedTest
	@DisplayName("verifica documento válido")
	@CsvSource({ "79744157038,true", "43134638000128,true", "987453895349,false" })
	void teste4(String documento, boolean resultadoEsperado) throws Exception {
		NovaCompraRequest request = new NovaCompraRequest("email@email.com", "Alberto", "Souza", documento, "endereco",
				"complemento", "salvador", 1l, 1l, "999999999", "400000", pedido);

		Assertions.assertEquals(resultadoEsperado, request.documentoValido());
	}
}
