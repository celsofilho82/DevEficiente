package jornada.deveficiente.casadocodigo.domain.dto;

public class FieldErrorOutputDto {

	private String field;
	private String message;

	public FieldErrorOutputDto(String field, String errorMessage) {
		this.field = field;
		this.message = errorMessage;
	}

	public String getField() {
		return field;
	}

	public String getMessage() {
		return message;
	}

}
