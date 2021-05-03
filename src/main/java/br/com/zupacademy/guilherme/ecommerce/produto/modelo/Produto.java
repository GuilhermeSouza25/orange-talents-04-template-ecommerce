package br.com.zupacademy.guilherme.ecommerce.produto.modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.zupacademy.guilherme.ecommerce.categoria.Categoria;
import br.com.zupacademy.guilherme.ecommerce.produto.ProibeCaracteristicasIguais;
import br.com.zupacademy.guilherme.ecommerce.produto.request.CaracteristicaRequest;
import br.com.zupacademy.guilherme.ecommerce.usuario.modelo.Usuario;
import io.jsonwebtoken.lang.Assert;

@Entity
public class Produto {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@NotNull
	@Positive
	private BigDecimal valor;
	
	@NotNull
	@Positive
	private Integer quantidade;
	
	@NotBlank
	@Size(max = 1000)
	private String descricao;
	
	@ManyToOne
	private Categoria categoria;
	
	@ManyToOne
	private Usuario usuario;
	
	@SuppressWarnings("unused")
	private LocalDateTime dataCadastro = LocalDateTime.now();
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.PERSIST)
	private Set<Caracteristica> caracteristicas = new HashSet<>();

	
	public Produto(@NotBlank String nome, @Positive BigDecimal valor, @Positive Integer quantidade,
			@NotBlank @Size(max = 1000) String descricao, Categoria categoria, Usuario usuario,
			@Valid @Size(min = 3) @ProibeCaracteristicasIguais Collection<CaracteristicaRequest> caracteristicas) {
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.categoria = categoria;
		this.usuario = usuario;
		Set<Caracteristica> novasCaracteristicas = caracteristicas
			.stream().map(caracteristica -> caracteristica.converter(this))
			.collect(Collectors.toSet());
		this.caracteristicas.addAll(novasCaracteristicas);
		
		Assert.isTrue(this.caracteristicas.size() >= 3, "O produto precisa ter pelo menos 3 características");
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}
	
	
	
}
