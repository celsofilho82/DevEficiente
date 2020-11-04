package jornada.deveficiente.casadocodigo.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jornada.deveficiente.casadocodigo.domain.model.Categoria;
import jornada.deveficiente.casadocodigo.domain.repository.CategoriaRepository;
import jornada.deveficiente.casadocodigo.domain.request.CategoriaRequest;


@Component
public class NomeCategoraNaoPodeSerDuplicadoValidator implements Validator {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return CategoriaRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		CategoriaRequest request = (CategoriaRequest) target;
		
		Optional<Categoria> possivelNomeCategoria = categoriaRepository.findByNome(request.getNome());
		if(possivelNomeCategoria.isPresent()) {
			errors.reject("nome", "O nome da categoria já está cadastrada " + request.getNome());
		}
	}

}
