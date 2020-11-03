package jornada.deveficiente.casadocodigo.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jornada.deveficiente.casadocodigo.domain.model.Autor;
import jornada.deveficiente.casadocodigo.domain.repository.AutorRepository;
import jornada.deveficiente.casadocodigo.domain.request.AutorRequest;

/*
 * Essa classe implementa o validator do Spring e dois métodos serão
 * implementados: Suport e Validate
 */

@Component
public class EmailAutorDeveSerUnicoValidador implements Validator {
	
	@Autowired
	private AutorRepository autorRepository;

	/*
	 * Esse método informa em qual tipo de parâmetro devemos aplicar a nossa
	 * validação.
	 * 
	 * Nesse caso a validação deve ser aplicada na Classe AutorRequest e
	 * verificamos se a classe que está chegando como argumento é a própria classe
	 * ou é filha dessa classe e isso é feito com o método isAssignableFrom
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return AutorRequest.class.isAssignableFrom(clazz);
	}

	/*
	 * Nessa classe vamos aplicar as validação customizadas mas antes verificamos se
	 * já não existem erros de validação padrão do spring
	 */
	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		AutorRequest request = (AutorRequest) target;
		Optional<Autor> possivelAutor = autorRepository.findByEmail(request.getEmail());
		
		if(possivelAutor.isPresent()) {
			errors.reject("email", "Esse email já está cadastrado " + request.getEmail());
		}

	}

}
