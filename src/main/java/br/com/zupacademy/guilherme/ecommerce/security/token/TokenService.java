package br.com.zupacademy.guilherme.ecommerce.security.token;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.zupacademy.guilherme.ecommerce.usuario.modelo.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {
	
	@Value("${mercadolivre.jwt.secret}")
	private String secret;
	
	public String gerarToken(Authentication authentication) {
		Usuario usuarioLogado = (Usuario) authentication.getPrincipal();
		
		Date dataToken = new Date();
		
		return Jwts.builder()
				.setIssuer("API do Mercado Livre")
				.setSubject(usuarioLogado.getId().toString())
				.setIssuedAt(dataToken)
				.setExpiration(new Date(dataToken.getTime() + 1800000))
				.signWith(SignatureAlgorithm.HS256, this.secret)
				.compact();
	}

	public Boolean validaToken(String token) {
		try {
			Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Long getUsuarioId(String token) {
		Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
		return Long.valueOf(claims.getSubject());
	}

}
