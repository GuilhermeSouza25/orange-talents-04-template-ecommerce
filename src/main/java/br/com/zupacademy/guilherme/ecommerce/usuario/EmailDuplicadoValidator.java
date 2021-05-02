package br.com.zupacademy.guilherme.ecommerce.usuario;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import br.com.zupacademy.guilherme.ecommerce.usuario.request.UsuarioRequest;

@Component
public class EmailDuplicadoValidator implements Validator {
	
	private EntityManager manager;
	
	//private UsuarioRepository repository;
	
	public EmailDuplicadoValidator(EntityManager manager) {
		
		this.manager = manager;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return UsuarioRequest.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
//		if (errors.hasErrors()) {
//			return;
//		}

		UsuarioRequest request = (UsuarioRequest) target;
		
		Boolean usuarioExistente = (Boolean) manager
    			.createQuery("SELECT count(u) > 0 FROM Usuario u WHERE login = :value")
    			.setParameter("value", request.getLogin())
    			.getSingleResult();
		
		if(usuarioExistente) {
			errors.rejectValue("login", null,
					"Já existe um(a) outro(a) usuario(a) com o mesmo email");
		}
		
		//Optional<Usuario> usuario = repository.findByLogin(request.getLogin());

//		if (usuario.isPresent()) {
//			errors.rejectValue("login", null,
//					"Já existe um(a) outro(a) usuario(a) com o mesmo email");
//		}
	}

}
