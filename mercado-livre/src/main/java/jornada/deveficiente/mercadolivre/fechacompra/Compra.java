package jornada.deveficiente.mercadolivre.fechacompra;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import jornada.deveficiente.mercadolivre.cadastroproduto.Produto;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@Valid
	@NotNull
	@ManyToOne
	private Produto produto;

	@Positive
	private int quantidade;

	@Valid
	@NotNull
	@ManyToOne
	private Usuario usuario;
	
	@NotNull
	@Enumerated
	private GatewayPagamento gatewayPagamento;

	public Compra(@Valid @NotNull Produto produto, @Positive int quantidade, @Valid @NotNull Usuario usuario, @NotNull GatewayPagamento gatewayPagamento) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.gatewayPagamento = gatewayPagamento;
	}

}
