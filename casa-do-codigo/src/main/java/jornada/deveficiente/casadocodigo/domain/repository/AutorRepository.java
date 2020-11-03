package jornada.deveficiente.casadocodigo.domain.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jornada.deveficiente.casadocodigo.domain.model.Autor;

@Repository
public interface AutorRepository extends CrudRepository<Autor, Long> {

	Optional<Autor> findByEmail(String email);

}
