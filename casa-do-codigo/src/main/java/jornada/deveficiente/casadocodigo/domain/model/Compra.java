package jornada.deveficiente.casadocodigo.domain.model;

import java.util.function.Function;

import javax.persistence.CascadeType;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

@Entity
public class Compra {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private @NotBlank @Email String email;
	private @NotBlank String nome;
	private @NotBlank String sobrenome;
	private @NotBlank String endereco;
	private @NotBlank String complemento;
	private @NotBlank String telefone;
	private @NotBlank String cep;
	@ManyToOne
	private @NotNull Pais pais;
	@ManyToOne
	private Estado estado;
	
	@OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST)
	private Pedido pedido;
	
	@Embedded
	private CupomAplicado cupomAplicado;

	public Long getId() {
		return id;
	}

	public Estado getEstado() {
		return estado;
	}

	public Compra(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String endereco, @NotBlank String complemento, @NotBlank String telefone, @NotBlank String cep,
			@NotNull Pais pais, Function<Compra, Pedido> funcaoCriacaoPedido) {
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.endereco = endereco;
		this.complemento = complemento;
		this.telefone = telefone;
		this.cep = cep;
		this.pais = pais;
		this.pedido = funcaoCriacaoPedido.apply(this);
	}

	public String getEmail() {
		return email;
	}

	public String getNome() {
		return nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public String getEndereco() {
		return endereco;
	}

	public String getComplemento() {
		return complemento;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCep() {
		return cep;
	}

	public Pais getPais() {
		return pais;
	}

	public void setEstado(@NotNull @Valid Estado estado) {
		Assert.isTrue(estado.pertenceAPais(pais), "Este estado não pertence ao País cadastrado na compra");
		this.estado = estado;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void aplicaCupom(Cupom cupom) {
		Assert.isTrue(cupom.valido(), "Este cupom já não é mais válido!");
		Assert.isNull(cupomAplicado, "Este cupom não pode ser substituido");
		this.cupomAplicado = new CupomAplicado(cupom);
		
	}

	public CupomAplicado getCupomAplicado() {
		return cupomAplicado;
	}

}
