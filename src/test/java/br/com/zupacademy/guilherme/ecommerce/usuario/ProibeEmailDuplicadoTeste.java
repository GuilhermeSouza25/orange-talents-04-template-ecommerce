//package br.com.zupacademy.guilherme.ecommerce.usuario;
//
//import java.util.Optional;
//import java.util.stream.Stream;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.mockito.Mockito;
//import org.springframework.validation.BeanPropertyBindingResult;
//import org.springframework.validation.Errors;
//
//public class ProibeEmailDuplicadoTeste {
//
//	@DisplayName("Deve lidar com email duplicado")
//	@ParameterizedTest
//	@MethodSource("geradorTeste1")
//	public void teste1(Optional<Usuario> usuario, boolean esperado) {
//
//		UsuarioRepository repository = Mockito.mock(UsuarioRepository.class);
//
//		EmailDuplicadoValidator validator = new EmailDuplicadoValidator(repository);
//
//		Object target = new UsuarioRequest("email@email.com", "123456");
//		Errors errors = new BeanPropertyBindingResult(target, "target");
//	
//		Mockito.when(repository.findByLogin("email@email.com")).thenReturn(usuario);
//		
//		validator.validate(target, errors);
//
//		Assertions.assertEquals(esperado, errors.hasFieldErrors("login"));
//	}
//	
//	
//	private static Stream<Arguments> geradorTeste1() {
//		Optional<Usuario> usuario = Optional.of(new Usuario("email@email.com", new SenhaLimpa("123456")));
//		return Stream.of(Arguments.of(usuario, true),
//				Arguments.of(Optional.empty(), false));
//	}
//}
