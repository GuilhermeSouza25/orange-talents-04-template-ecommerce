package br.com.zupacademy.guilherme.ecommerce.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.zupacademy.guilherme.ecommerce.security.token.TokenService;
import br.com.zupacademy.guilherme.ecommerce.usuario.modelo.Usuario;

public class AuthTokenFilter extends OncePerRequestFilter {
	
	private TokenService tokenService;
	private AutenticacaoService autenticacaoService;
	
	public AuthTokenFilter(
			TokenService tokenService,
			AutenticacaoService autenticacaoService) {
		this.tokenService = tokenService;
		this.autenticacaoService = autenticacaoService;
	}
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, 
			HttpServletResponse response, 
			FilterChain filterChain) throws ServletException, IOException {
		
		String token = recuperaToken(request);
		Boolean tokenValido = tokenService.validaToken(token);
		if(tokenValido) {
			autenticaUsuario(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void autenticaUsuario(String token) {
		Long idUsuario = tokenService.getUsuarioId(token);
		Usuario usuario = autenticacaoService.findUsuarioById(idUsuario);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String recuperaToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		return token.substring(7);
	}

}
