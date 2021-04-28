package br.com.zupacademy.guilherme.ecommerce.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zupacademy.guilherme.ecommerce.shared.validators.UniqueValue;

public class UsuarioRequest {
	
	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "login")
	private String login;
	
	@NotBlank
	@Size(min = 6)
	private String senha;
	
	
	public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
		this.login = login;
		this.senha = senha;
	}

	public String getLogin() {
		return login;
	}
	
	public Usuario converter() {
		//como este ponto do codigo sabe que deve passar a senha limpa?
		return new Usuario(login, new SenhaLimpa(this.senha));
	}
}
