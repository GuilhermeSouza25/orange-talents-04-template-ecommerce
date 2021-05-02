package br.com.zupacademy.guilherme.ecommerce.usuario;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

/**
 * Representa uma senha limpa na aplicação
 * @author Jose
 */
public class SenhaLimpa {
	
	private String senha;

	public SenhaLimpa(@NotBlank @Size(min = 6) String senha) {
		
		Assert.hasLength(senha, "A senha nao pode ser em branco");
		Assert.isTrue(senha.length()>=6,"A senha tem que ter no mínimo 6 caracteres");
		
		this.senha = senha;
	}
	
	public String hash() {
		return new BCryptPasswordEncoder().encode(this.senha);
	}
}
