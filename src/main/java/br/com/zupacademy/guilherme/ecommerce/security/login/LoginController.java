package br.com.zupacademy.guilherme.ecommerce.security.login;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.guilherme.ecommerce.security.token.TokenResponse;
import br.com.zupacademy.guilherme.ecommerce.security.token.TokenService;
import br.com.zupacademy.guilherme.ecommerce.shared.Erro;

@RestController
@RequestMapping("/api/login")
@Profile(value = {"prod", "test"})
public class LoginController {
	
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	private ResponseEntity<?> autenticar(@RequestBody @Valid LoginRequest loginRequest) {
		
		UsernamePasswordAuthenticationToken dadosLogin = loginRequest.converter();
		
		try {
			//aqui chama o AutenticacaoService
			Authentication authentication = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authentication);
			
			return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().body(new Erro("Login ou senha inválidos"));
		}
	}
}
