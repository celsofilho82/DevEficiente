package jornada.deveficiente.casadocodigo.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jornada.deveficiente.casadocodigo.domain.model.Estado;
import jornada.deveficiente.casadocodigo.domain.model.Pais;
import jornada.deveficiente.casadocodigo.domain.request.NovaCompraRequest;

@Component
public class EstadoPertencePaisValidator implements Validator {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean supports(Class<?> clazz) {

		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if (errors.hasErrors()) {
			return;
		}

		NovaCompraRequest request = (NovaCompraRequest) target;

		if (request.temEstado()) {

			Pais pais = manager.find(Pais.class, request.getIdpais());
			Estado estado = manager.find(Estado.class, request.getIdEstado());
			if (!estado.pertenceAPais(pais)) {
				errors.reject("idEstado", null, "Este estado não pertence ao Pais selecionado");
			}

		}

	}

}
