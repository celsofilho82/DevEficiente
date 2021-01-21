package jornada.deveficiente.mercadolivre.fechacompra;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import jornada.deveficiente.mercadolivre.cadastroproduto.Produto;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;
import jornada.deveficiente.mercadolivre.transacoes.Transacao;

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

	@OneToMany(mappedBy = "compra", cascade = CascadeType.MERGE)
	private Set<Transacao> transacoes = new HashSet<Transacao>();

	@Deprecated
	public Compra() {
	}

	public Compra(@Valid @NotNull Produto produto, @Positive int quantidade, @Valid @NotNull Usuario usuario,
			@NotNull GatewayPagamento gatewayPagamento) {
		this.produto = produto;
		this.quantidade = quantidade;
		this.usuario = usuario;
		this.gatewayPagamento = gatewayPagamento;
	}

	public void adicionaTransacao(@Valid RetornoGatewayPagamento request) {
		Transacao novaTransacao = request.toTransacao(this);

		Assert.isTrue(!this.transacoes.contains(novaTransacao), "Já existe uma transação com esse mesmo id!!!");

		Set<Transacao> transacoesConcluidasComSucesso = this.transacoes.stream().filter(Transacao::concluidaComSucesso)
				.collect(Collectors.toSet());

		Assert.isTrue(transacoesConcluidasComSucesso.isEmpty(), "Essa compra já foi concluida com Sucesso!!!");

		this.transacoes.add(novaTransacao);

	}

}
