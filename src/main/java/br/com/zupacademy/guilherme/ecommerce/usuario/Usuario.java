package br.com.zupacademy.guilherme.ecommerce.usuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

@Entity
public class Usuario {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email
	private String login;
	
	@NotBlank
	@Size(min = 6)
	private String senha;
	
	@PastOrPresent
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	public Usuario() {}

	public Usuario(
			@NotBlank @Email String login, 
			@Valid @NotNull SenhaLimpa senhaLimpa) {
		
		Assert.isTrue(StringUtils.hasLength(login),"O email não pode ser em branco");
		Assert.notNull(senhaLimpa,"O objeto do tipo senha limpa nao pode ser nulo");
		
		this.login = login;
		this.senha = senhaLimpa.hash();
		
	}
	
}




