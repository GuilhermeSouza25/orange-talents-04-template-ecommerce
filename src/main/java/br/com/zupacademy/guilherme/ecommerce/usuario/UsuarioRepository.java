package br.com.zupacademy.guilherme.ecommerce.usuario;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zupacademy.guilherme.ecommerce.usuario.modelo.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Optional<Usuario> findByLogin(String Login);

}
