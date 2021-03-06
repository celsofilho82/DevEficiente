package jornada.deveficiente.casadocodigo.domain.model;

import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class CupomTest {

	@ParameterizedTest
	@CsvSource({
		"0,true",
		"-1,false",
		"1,true",
		"-10,false"		
	})
	void teste1(long valor,boolean resultado) throws Exception {
		Cupom cupom = new Cupom("codigo", 10, LocalDate.now().plusDays(valor));
		Assertions.assertEquals(resultado, cupom.valido());
	}
}

