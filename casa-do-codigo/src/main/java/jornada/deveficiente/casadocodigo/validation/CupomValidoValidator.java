package jornada.deveficiente.casadocodigo.validation;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import jornada.deveficiente.casadocodigo.domain.model.Cupom;
import jornada.deveficiente.casadocodigo.domain.repository.CupomRepository;
import jornada.deveficiente.casadocodigo.domain.request.NovaCompraRequest;

@Component
public class CupomValidoValidator implements Validator{
	
	@Autowired
	private CupomRepository cupomRepository;
	

	@Override
	public boolean supports(Class<?> clazz) {
		return NovaCompraRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(errors.hasErrors()) {
			return ;
		}
		
		NovaCompraRequest request = (NovaCompraRequest) target;
		Optional<String> possivelCodigo = request.getCodigoCupom();
		if(possivelCodigo.isPresent()) {
			Cupom cupom = cupomRepository.findByCodigo(possivelCodigo.get());
			if(!cupom.valido()) {
				errors.rejectValue("codigoCupom", null, "Este cupom não é mais válido");
			}
		}
	}

}