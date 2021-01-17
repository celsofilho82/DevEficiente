package jornada.deveficiente.mercadolivre.detalhesproduto;

import java.math.BigDecimal;
import java.util.Set;

import jornada.deveficiente.mercadolivre.cadastroproduto.Produto;

// Esqueci de implementar as caracteristicas do produto. Segue o exemplo do código
// this.caracteristicas = produto.getCaracteristicas().stream()
//         .map(caracteristica -> new DetalheProdutoCaracteristicaResponse(
//                 caracteristica)).collect(Collectors.toSet());

public class DetalhesProdutoResponse {

	private String descricao;
	private String nome;
	private BigDecimal preco;
	private Set<String> linksImagens;
	private Set<String> perguntas;

	public DetalhesProdutoResponse(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());

	}

	public String getDescricao() {
		return descricao;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public Set<String> getLinksImagens() {
		return linksImagens;
	}

	public Set<String> getPerguntas() {
		return perguntas;
	}

}
