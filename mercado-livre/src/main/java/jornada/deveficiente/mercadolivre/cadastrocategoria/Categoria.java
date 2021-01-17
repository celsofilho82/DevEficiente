package jornada.deveficiente.mercadolivre.cadastrocategoria;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private @NotBlank String nome;

	@ManyToOne
	private Categoria categoriaMae;

	@Deprecated
	public Categoria() {
	}

	public Categoria(@NotBlank String nome) {
		this.nome = nome;

	}

	public void setCategoriaMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;

	}

	public String getNome() {
		return nome;
	}

	public Long getId() {
		return id;
	}

	public Categoria getCategoriaMae() {
		return categoriaMae;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoriaMae == null) ? 0 : categoriaMae.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Categoria other = (Categoria) obj;
		if (categoriaMae == null) {
			if (other.categoriaMae != null)
				return false;
		} else if (!categoriaMae.equals(other.categoriaMae))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
