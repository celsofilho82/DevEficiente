package jornada.deveficiente.mercadolivre.seguranca;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;

import jornada.deveficiente.mercadolivre.cadastrousuario.Usuario;

@Configuration
public class AppUserDetailsMapper implements UserDetailsMapper {

	@Override
	public UserDetails map(Object shouldBeASystemUser) {
		return new UsuarioLogado((Usuario) shouldBeASystemUser);
	}

}