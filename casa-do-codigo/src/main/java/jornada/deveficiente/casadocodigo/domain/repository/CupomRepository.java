package jornada.deveficiente.casadocodigo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jornada.deveficiente.casadocodigo.domain.model.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {

	/**
	 * Busca por um cupom que existe no sistema
	 * @param codigo
	 * @return
	 */
	public Cupom findByCodigo(String codigo);
}
