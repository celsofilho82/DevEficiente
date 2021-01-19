package jornada.deveficiente.mercadolivre.transacoes;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import jornada.deveficiente.mercadolivre.fechacompra.Compra;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private StatusTransacao statusTransacao;

	@NotBlank
	private String idTransacao;

	@NotNull
	@Future
	private LocalDateTime instanteTransacao;

	@Valid
	@NotNull
	@ManyToOne
	private Compra compra;

	@Deprecated
	public Transacao() {
	}

	public Transacao(StatusTransacao statusTransacao, @NotBlank String idTransacao, Compra compra) {
		this.statusTransacao = statusTransacao;
		this.idTransacao = idTransacao;
		this.compra = compra;
		this.instanteTransacao = LocalDateTime.now();
	}

	public boolean concluidaComSucesso() {
		return this.statusTransacao.equals(StatusTransacao.sucesso);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransacao == null) ? 0 : idTransacao.hashCode());
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
		Transacao other = (Transacao) obj;
		if (idTransacao == null) {
			if (other.idTransacao != null)
				return false;
		} else if (!idTransacao.equals(other.idTransacao))
			return false;
		return true;
	}

}
