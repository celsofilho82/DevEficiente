package jornada.deveficiente.casadocodigo.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jornada.deveficiente.casadocodigo.domain.request.NovaCompraRequest;

public class VerificaDocumentoCpfOuCnpjValidator implements Validator {

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
		
		if (!request.documentoValido()) {
			errors.reject("documento", null, "documento precisa ser cpf ou cnpj");
		}
	}

}
