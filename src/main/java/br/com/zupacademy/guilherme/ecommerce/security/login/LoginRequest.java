package br.com.zupacademy.guilherme.ecommerce.security.login;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginRequest {
	
	@NotBlank
	@Email
	private String login;
	
	@NotBlank
	private String senha;
	
	
	public LoginRequest(@NotBlank @Email String login, @NotBlank String senha) {
		this.login = login;
		this.senha = senha;
	}


	public UsernamePasswordAuthenticationToken converter() {
		
		return new UsernamePasswordAuthenticationToken(login, senha);
	}
}
