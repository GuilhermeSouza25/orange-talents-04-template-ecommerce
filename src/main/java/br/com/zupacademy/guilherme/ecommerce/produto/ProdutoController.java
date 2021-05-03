package br.com.zupacademy.guilherme.ecommerce.produto;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilherme.ecommerce.produto.modelo.Produto;
import br.com.zupacademy.guilherme.ecommerce.produto.request.ProdutoRequest;
import br.com.zupacademy.guilherme.ecommerce.usuario.modelo.Usuario;

@RestController
public class ProdutoController {
	
	@PersistenceContext
	EntityManager manager;
	
	@PostMapping("/api/produto")
	@Transactional
	public ResponseEntity<?> cadastrar(
			@RequestBody @Valid ProdutoRequest produtoRequest,
			@AuthenticationPrincipal Usuario usuario) {
		
		Produto produto = produtoRequest.converter(manager, usuario);
		manager.persist(produto);
		
		return ResponseEntity.ok(produto);
	}
}
