package br.com.zupacademy.guilherme.ecommerce.usuario.modelo;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.security.core.GrantedAuthority;

@Entity
@SuppressWarnings("serial")
public class Role implements GrantedAuthority{
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private RoleName nome;
	
	@Deprecated
	public Role() {}
	
	public Role(RoleName name) {
		this.nome = name;
	}

	@Override
	public String getAuthority() {
		return this.nome.toString();
	}
}
	
