package jornada.deveficiente.casadocodigo.domain.dto;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorsOutputDto {

	private List<String> globalErrorMessages = new ArrayList<>();
	private List<FieldErrorOutputDto> fieldErrors = new ArrayList<>();
	
	
	public void addError(String errorMessage) {
		getGlobalErrorMessages().add(errorMessage);
	}

	public void addFieldError(String field, String errorMessage) {
		FieldErrorOutputDto fieldError = new FieldErrorOutputDto(field, errorMessage);
		getErrors().add(fieldError);
	}

	public List<String> getGlobalErrorMessages() {
		return globalErrorMessages;
	}

	public List<FieldErrorOutputDto> getErrors() {
		return fieldErrors;
	}
	
	

}
