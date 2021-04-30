package br.com.zupacademy.guilherme.ecommerce.categoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping("/api/categoria")
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid CategoriaRequest request) {
		
		Categoria categoria = request.converter(manager);
		
		manager.persist(categoria);
		
		return ResponseEntity.ok().build();
	}
}
