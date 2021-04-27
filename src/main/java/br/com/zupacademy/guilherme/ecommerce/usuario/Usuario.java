package br.com.zupacademy.guilherme.ecommerce.usuario;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

	public Usuario(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		this.login = login;
		this.senha = encoder.encode(senha);
		
	}
	
}




