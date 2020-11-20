package jornada.deveficiente.casadocodigo.domain.response;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

import jornada.deveficiente.casadocodigo.domain.model.Compra;
import jornada.deveficiente.casadocodigo.domain.model.CupomAplicado;
import jornada.deveficiente.casadocodigo.domain.model.ItemPedido;

public class DetalhesCompraResponse {

	private String nome;
	private String sobrenome;
	private String email;
	private String endereco;
	private String cep;
	private CupomAplicado cupom;
	private Set<ItemPedido> itens;
	private BigDecimal total;

	public DetalhesCompraResponse(Compra compra) {
		this.nome = compra.getNome();
		this.sobrenome = compra.getSobrenome();
		this.email = compra.getEmail();
		this.endereco = compra.getEndereco();
		this.cep = compra.getCep();
		this.cupom = compra.getCupomAplicado();
		this.setItens(compra.getPedido().getItens());
		this.total = compra.getPedido().getTotalPedido();
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getEmail() {
		return email;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getCep() {
		return cep;
	}

	public CupomAplicado getCupom() {
		return cupom;
	}

	public Set<String> getItens() {
		return itens.stream().map(item -> item.getLivro().getTitulo()).collect(Collectors.toSet());
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setItens(Set<ItemPedido> itens) {
		this.itens = itens;
	}

}
