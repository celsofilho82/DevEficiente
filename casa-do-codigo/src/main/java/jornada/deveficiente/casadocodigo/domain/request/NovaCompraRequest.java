package jornada.deveficiente.casadocodigo.domain.request;

import java.util.Optional;
import java.util.function.Function;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator;
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import jornada.deveficiente.casadocodigo.domain.model.Compra;
import jornada.deveficiente.casadocodigo.domain.model.Cupom;
import jornada.deveficiente.casadocodigo.domain.model.Estado;
import jornada.deveficiente.casadocodigo.domain.model.Pais;
import jornada.deveficiente.casadocodigo.domain.model.Pedido;
import jornada.deveficiente.casadocodigo.domain.repository.CupomRepository;
import jornada.deveficiente.casadocodigo.validation.ExistsId;

public class NovaCompraRequest {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String nome;

	@NotBlank
	private String sobrenome;

	@NotBlank
	private String documento;

	@NotBlank
	private String endereco;

	@NotBlank
	private String complemento;

	@NotBlank
	private String cidade;

	@NotNull
	@ExistsId(domainClass = Pais.class, fieldName = "id")
	private Long idPais;

	@NotNull
	@ExistsId(domainClass = Estado.class, fieldName = "id")
	private Long idEstado;

	@NotBlank
	private String telefone;

	@NotBlank
	private String cep;

	@Valid
	@NotNull
	private NovoPedidoRequest pedido;
	
	private String codigoCupom;

	public NovaCompraRequest(@NotBlank @Email String email, @NotBlank String nome, @NotBlank String sobrenome,
			@NotBlank String documento, @NotBlank String endereco, @NotBlank String complemento,
			@NotBlank String cidade, @NotNull Long idPais, @NotNull Long idEstado, @NotBlank String telefone,
			@NotBlank String cep, @Valid @NotNull NovoPedidoRequest pedido) {
		super();
		this.email = email;
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.documento = documento;
		this.endereco = endereco;
		this.complemento = complemento;
		this.cidade = cidade;
		this.idPais = idPais;
		this.idEstado = idEstado;
		this.telefone = telefone;
		this.cep = cep;
		this.pedido = pedido;
	}

	public Long getIdpais() {
		return idPais;
	}

	public Long getIdEstado() {
		return idEstado;
	}

	public boolean documentoValido() {
		Assert.hasLength(documento, "O documento precisa ser preenchido!");

		CPFValidator cpfValidator = new CPFValidator();
		cpfValidator.initialize(null);

		CNPJValidator cnpjValidator = new CNPJValidator();
		cnpjValidator.initialize(null);

		return cpfValidator.isValid(this.documento, null) || cnpjValidator.isValid(this.documento, null);
	}

	public Compra toModel(EntityManager manager, CupomRepository cupomRepository) {
		@NotNull Pais pais = manager.find(Pais.class, idPais);
		
		Function<Compra, Pedido> funcaoCriacaoPedido = pedido.toModel(manager);
		
		Compra compra = new Compra(email,nome,sobrenome,endereco,complemento,telefone,cep, pais, funcaoCriacaoPedido);
		if (idEstado != null) {
			compra.setEstado(manager.find(Estado.class, idEstado));
		}
		
		if(StringUtils.hasText(codigoCupom)) {
			Cupom cupom = cupomRepository.findByCodigo(codigoCupom);
			compra.aplicaCupom(cupom);
		}
		
		
		return compra;
	}

	public boolean temEstado() {
		return idEstado != null;
	}

	public void setCodigoCupom(String codigoCupom) {
		this.codigoCupom = codigoCupom;
	}

	public Optional<String> getCodigoCupom() {
		return Optional.ofNullable(codigoCupom);
	}

}
