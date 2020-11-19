package jornada.deveficiente.casadocodigo.domain.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

import jornada.deveficiente.casadocodigo.domain.model.Compra;
import jornada.deveficiente.casadocodigo.domain.model.ItemPedido;
import jornada.deveficiente.casadocodigo.domain.model.Pedido;

public class NovoPedidoRequest {

	@Positive
	@NotNull
	private BigDecimal total;

	@Size(min = 1)
	@Valid
	private List<NovoPedidoItemRequest> itens = new ArrayList<>();

	public NovoPedidoRequest(@Positive @NotNull BigDecimal total,
			@Size(min = 1) @Valid List<NovoPedidoItemRequest> itens) {
		super();
		this.total = total;
		this.itens = itens;
	}

	public Function<Compra, Pedido> toModel(EntityManager manager) {
		Set<ItemPedido> itensCalculados = itens.stream().map(item -> item.toModel(manager)).collect(Collectors.toSet());
		//Lazy evaluation
		return (compra) -> {
			Pedido pedido = new Pedido(compra, itensCalculados);
			Assert.isTrue(pedido.totalIgual(total), "Total enviado está divergindo do calculado");
			return pedido; 
		};
		
	}

}
