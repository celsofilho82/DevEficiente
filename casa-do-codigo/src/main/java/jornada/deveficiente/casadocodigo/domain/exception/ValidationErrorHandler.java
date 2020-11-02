package jornada.deveficiente.casadocodigo.domain.exception;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jornada.deveficiente.casadocodigo.domain.dto.ValidationErrorsOutputDto;

@RestControllerAdvice
public class ValidationErrorHandler {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
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
