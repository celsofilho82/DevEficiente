package jornada.deveficiente.casadocodigo.domain.request;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import jornada.deveficiente.casadocodigo.domain.model.ItemPedido;
import jornada.deveficiente.casadocodigo.domain.model.Livro;
import jornada.deveficiente.casadocodigo.validation.ExistsId;

public class NovoPedidoItemRequest {

	@NotNull
	@ExistsId(domainClass = Livro.class, fieldName = "id")
	private Long idLivro;

	@Positive
	private int quantidade;

	public NovoPedidoItemRequest(@NotNull Long idLivro, @Positive int quantidade) {
		super();
		this.idLivro = idLivro;
		this.quantidade = quantidade;
	}

	public ItemPedido toModel(EntityManager manager) {
		@NotNull Livro livro = manager.find(Livro.class, idLivro);
		return new ItemPedido(livro, quantidade);
	}

}
