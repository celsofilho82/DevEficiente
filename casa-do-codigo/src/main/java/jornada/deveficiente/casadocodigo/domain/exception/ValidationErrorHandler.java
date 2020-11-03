package jornada.deveficiente.casadocodigo.domain.exception;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jornada.deveficiente.casadocodigo.domain.dto.ValidationErrorsOutputDto;

/*
 * Está classe é um Interceptor, criamos essa classe separada para manipular
 * os erros de validação e exibir uma mensagem mais amigável para o cliente
 * da API. Todas as excesões do tipo "MethodArgumentNotValidException" em
 * todos os controllers do projeto utilizaram essa classe de validação
 * pois essa anotação @RestControllerAdvice vai abranger todos os controllers
 */

@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler({MethodArgumentNotValidException.class, DataIntegrityViolationException.class} )
	public ValidationErrorsOutputDto handleValidationError(MethodArgumentNotValidException exception) {
		List<ObjectError> globalError = exception.getBindingResult().getGlobalErrors();
		List<FieldError> fieldError = exception.getBindingResult().getFieldErrors();
		return buildValidationErrors(globalError, fieldError);
	}

	private ValidationErrorsOutputDto buildValidationErrors(List<ObjectError> globalError,
			List<FieldError> fieldError) {

		ValidationErrorsOutputDto validationErrors = new ValidationErrorsOutputDto();

		globalError.forEach(error -> validationErrors.addError(getErrorMessage(error)));

		fieldError.forEach(error -> {
			String errorMessage = getErrorMessage(error);
			validationErrors.addFieldError(error.getField(), errorMessage);
		});
		return validationErrors;
	}

	private String getErrorMessage(ObjectError error) {
		return messageSource.getMessage(error, LocaleContextHolder.getLocale());
	}

}
