package jornada.deveficiente.casadocodigo.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jornada.deveficiente.casadocodigo.domain.model.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

	Optional<Categoria> findByNome(String nome);

}
