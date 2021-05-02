package br.com.zupacademy.guilherme.ecommerce.usuario.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import br.com.zupacademy.guilherme.ecommerce.usuario.SenhaLimpa;

@Entity
@SuppressWarnings("serial")
public class Usuario implements UserDetails {
	
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
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Role> roles = new ArrayList<>();
	
	public Usuario() {}

	public Usuario(
			@NotBlank @Email String login, 
			@Valid @NotNull SenhaLimpa senhaLimpa) {
		
		Assert.isTrue(StringUtils.hasLength(login),"O email não pode ser em branco");
		Assert.notNull(senhaLimpa,"O objeto do tipo senha limpa nao pode ser nulo");
		
		this.login = login;
		this.senha = senhaLimpa.hash();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}
	
	public Long getId() {
		return id;
	}
	
	@Override
	public String getPassword() {
		return this.senha;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}




