package jornada.deveficiente.mercadolivre.cadastroproduto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import jornada.deveficiente.mercadolivre.adicionaopiniao.Opiniao;
import jornada.deveficiente.mercadolivre.adicionapergunta.Pergunta;
import jornada.deveficiente.mercadolivre.cadastrocategoria.Categoria;
import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank String nome;
	private @NotBlank @Positive BigDecimal valor;
	private @NotNull @Positive int quantidade;
	private LocalDate dataCadastro;

	@NotNull
	@Valid
	@ManyToOne
	private Categoria categoria;

	public Set<ImagemProduto> getImagens() {
		return imagens;
	}

	@NotNull
	@Valid
	@ManyToOne
	private Usuario usuario;

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	@OneToMany(mappedBy = "produto", cascade = CascadeType.MERGE)
	private Set<ImagemProduto> imagens = new HashSet<ImagemProduto>();

	@OneToMany(mappedBy = "produto")
	private Set<Pergunta> perguntas = new HashSet<Pergunta>();

	@OneToMany(mappedBy = "produto")
	private Set<Opiniao> opinioes = new HashSet<Opiniao>();

	@Deprecated
	public Produto() {
	}

	public Produto(@NotBlank String nome, @NotBlank @Length(max = 1000) String descricao,
			@NotBlank @Positive BigDecimal valor, @NotNull @Positive int quantidade, LocalDate dataCadastro,
			@NotNull @Valid Categoria categoria, Usuario usuario) {
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.quantidade = quantidade;
		this.dataCadastro = dataCadastro;
		this.categoria = categoria;
		this.usuario = usuario;

	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public String getDescricao() {
		return descricao;
	}

	public void associaImagens(Set<String> links) {
		Set<ImagemProduto> imagens = links.stream().map(link -> new ImagemProduto(this, link))
				.collect(Collectors.toSet());

		this.imagens.addAll(imagens);
	}

	public boolean pertenceAoUsuario(Usuario dono) {
		return this.usuario.equals(dono);
	}

	public <T> Set<T> mapImagens(Function<ImagemProduto, T> funcaoMapper) {
		return this.imagens.stream().map(funcaoMapper).collect(Collectors.toSet());
	}

	public <T> Set<T> mapPerguntas(Function<Pergunta, T> funcaoMapper) {
		return this.perguntas.stream().map(funcaoMapper).collect(Collectors.toSet());
	}

	public <T> Set<T> mapOpinioes(Function<Opiniao, T> funcaoMapper) {
		return this.opinioes.stream().map(funcaoMapper).collect(Collectors.toSet());
	}

	public boolean abateEstoque(@Positive int quantidade) {
		Assert.isTrue(quantidade > 0, "Para abater o estoque a quantidade deve ser maior que 0");
		if (quantidade <= this.quantidade) {
			this.quantidade -= quantidade;
			return true;
		}

		return false;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (valor == null) {
			if (other.valor != null)
				return false;
		} else if (!valor.equals(other.valor))
			return false;
		return true;
	}

}
