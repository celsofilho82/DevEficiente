package jornada.deveficiente.mercadolivre.detalhesproduto;

import java.math.BigDecimal;
import java.util.Map;
import java.util.OptionalDouble;
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
	private Set<Map<String, String>> opinioes;
	private double mediaOpinioes;

	public DetalhesProdutoResponse(Produto produto) {
		this.descricao = produto.getDescricao();
		this.nome = produto.getNome();
		this.preco = produto.getValor();
		this.linksImagens = produto.mapImagens(imagem -> imagem.getLink());
		this.perguntas = produto.mapPerguntas(pergunta -> pergunta.getTitulo());
		this.opinioes = produto.mapOpinioes(opiniao -> {
			return Map.of("titulo", opiniao.getTitulo(), "descricao", opiniao.getDescricao());
		});
		this.mediaOpinioes = calculaMediaOpinioes(produto);

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

	public Set<Map<String, String>> getOpinioes() {
		return opinioes;
	}

	public double getMediaOpinioes() {
		return mediaOpinioes;
	}

	private double calculaMediaOpinioes(Produto produto) {
		Set<Integer> notas = produto.mapOpinioes(opiniao -> opiniao.getNota());
		OptionalDouble possivelMedia = notas.stream().mapToInt(nota -> nota).average();
		return possivelMedia.orElse(0.0);
	}

}
