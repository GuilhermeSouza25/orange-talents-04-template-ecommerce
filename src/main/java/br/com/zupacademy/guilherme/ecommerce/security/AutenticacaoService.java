package br.com.zupacademy.guilherme.ecommerce.security;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.zupacademy.guilherme.ecommerce.usuario.modelo.Usuario;

@Service
public class AutenticacaoService implements UserDetailsService {
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = manager
				.createQuery("SELECT u FROM Usuario u WHERE u.login = :login", Usuario.class)
				.setParameter("login", username).getSingleResult();
		return usuario;
	}
	
	public Usuario findUsuarioById(Long id) {
		return manager.find(Usuario.class, id);
	}
}
