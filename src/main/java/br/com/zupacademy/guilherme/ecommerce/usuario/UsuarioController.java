package br.com.zupacademy.guilherme.ecommerce.usuario;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/api/usuario")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid UsuarioRequest usuarioRequest) {
		
		Usuario usuario = usuarioRequest.converter();
		
		manager.persist(usuario);
		
		return ResponseEntity.ok().build();
	}
}
