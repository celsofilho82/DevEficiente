package jornada.deveficiente.casadocodigo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import jornada.deveficiente.casadocodigo.domain.model.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {

}
