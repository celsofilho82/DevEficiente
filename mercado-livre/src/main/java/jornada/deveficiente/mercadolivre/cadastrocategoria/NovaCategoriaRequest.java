package jornada.deveficiente.mercadolivre.cadastrocategoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

public class NovaCategoriaRequest {

	@NotBlank
	private String nome;

	@Positive
	private Long idCategoriaMae;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}

	public Categoria toModel(EntityManager manager) {
		Categoria categoria = new Categoria(this.nome);
		if (idCategoriaMae != null) {
			Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
			Assert.notNull(categoriaMae, "O ID da categoria mãe não pode ser nulo!");
			categoria.setCategoriaMae(categoriaMae);
		}

		return categoria;
	}
}
