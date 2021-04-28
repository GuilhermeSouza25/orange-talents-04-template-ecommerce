package br.com.zupacademy.guilherme.ecommerce.usuario;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
		
		List<?> resultList = manager
    			.createQuery("SELECT 1 FROM Usuario WHERE login = :value")
    			.setParameter("value", request.getLogin())
    			.getResultList();
		
		//Optional<Usuario> usuario = repository.findByLogin(request.getLogin());
		

		if (!resultList.isEmpty()) {
			errors.rejectValue("login", null,
					"Já existe um(a) outro(a) usuario(a) com o mesmo email");
		}
	}

}
