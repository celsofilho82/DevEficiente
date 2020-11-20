package jornada.deveficiente.casadocodigo.domain.model;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Valid
	@OneToOne
	private Compra compra;

	private BigDecimal totalPedido;

	public Long getId() {
		return id;
	}

	public Set<ItemPedido> getItens() {
		return itens;
	}

	@Size(min = 1)
	@ElementCollection
	private Set<ItemPedido> itens = new HashSet<ItemPedido>();

	@Deprecated
	public Pedido() {
	}

	public Pedido(@NotNull @Valid Compra compra, @Size(min = 1) Set<ItemPedido> itens) {
		Assert.isTrue(!itens.isEmpty(), "O pedido deve possuir 1 item");
		this.compra = compra;
		this.itens.addAll(itens);
	}

	public BigDecimal getTotalPedido() {
		return totalPedido;
	}

	public boolean totalIgual(@Positive @NotNull BigDecimal total) {
		BigDecimal totalPedido = valorTotal();
		return totalPedido.doubleValue() == total.doubleValue();
	}

	private BigDecimal valorTotal() {
		totalPedido = itens.stream().map(ItemPedido::total).reduce(BigDecimal.ZERO,
				(atual, proximo) -> atual.add(proximo));
		return getTotalPedido();
	}

}
