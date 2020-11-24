package jornada.deveficiente.mercadolivre.cadastrousuario;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class UsuarioLoginUnicoValidator implements Validator {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public boolean supports(Class<?> clazz) {
		return NovoUsuarioRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		NovoUsuarioRequest request = (NovoUsuarioRequest) target;
		Optional<Usuario> possivelUsuario = usuarioRepository.findByLogin(request.getLogin());

		if (possivelUsuario.isPresent()) {
			errors.reject("login", "Esse login já está cadastrado " + request.getLogin());
		}
	}

}
