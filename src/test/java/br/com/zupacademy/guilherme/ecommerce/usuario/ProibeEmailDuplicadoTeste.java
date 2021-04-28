package br.com.zupacademy.guilherme.ecommerce.usuario;

import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

public class ProibeEmailDuplicadoTeste {

	@DisplayName("Deve lidar com email duplicado")
	@ParameterizedTest
	@MethodSource("geradorTeste1")
	public void teste1(Usuario possivelUsuario, boolean esperado) {

		EntityManager manager = Mockito.mock(EntityManager.class);

		EmailDuplicadoValidator validator = new EmailDuplicadoValidator(manager);

		Object target = new UsuarioRequest("email@email.com", "123456");
		Errors errors = new BeanPropertyBindingResult(target, "teste");
		
		System.out.println(manager);
		
		Mockito.when(manager.createQuery("SELECT u FROM Usuario u WHERE u.login = 'email@email.com'")
				.getSingleResult()).thenReturn(possivelUsuario);

		validator.validate(target, errors);

		Assertions.assertEquals(esperado, errors.hasFieldErrors("login"));
	}
	
	
	private static Stream<Arguments> geradorTeste1() {
		Usuario usuario = new Usuario("email@email.com", new SenhaLimpa("123456"));
		return Stream.of(Arguments.of(usuario, true),
				Arguments.of(null, false));
	}
}
