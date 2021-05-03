package br.com.zupacademy.guilherme.ecommerce.produto.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.sun.istack.NotNull;

import br.com.zupacademy.guilherme.ecommerce.categoria.Categoria;
import br.com.zupacademy.guilherme.ecommerce.produto.ProibeCaracteristicasIguais;
import br.com.zupacademy.guilherme.ecommerce.produto.modelo.Produto;
import br.com.zupacademy.guilherme.ecommerce.shared.validators.ExistsId;
import br.com.zupacademy.guilherme.ecommerce.shared.validators.UniqueValue;
import br.com.zupacademy.guilherme.ecommerce.usuario.modelo.Usuario;

public class ProdutoRequest {
	
	@NotBlank
	@UniqueValue(domainClass = Produto.class, fieldName = "nome")
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
	
	@NotBlank
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private String idCategoria;
	
	@Valid
	@Size(min = 3)
	@ProibeCaracteristicasIguais
	private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();

	public ProdutoRequest(@NotBlank String nome, @Positive BigDecimal valor, @Positive Integer quantidade,
			@NotBlank @Size(max = 1000) String descricao,
			@ExistsId(domainClass = Categoria.class, fieldName = "id") String idCategoria,
			List<CaracteristicaRequest> caracteristicas) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidade = quantidade;
		this.descricao = descricao;
		this.idCategoria = idCategoria;
		this.caracteristicas.addAll(caracteristicas);
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getIdCategoria() {
		return idCategoria;
	}
	
	public List<CaracteristicaRequest> getCaracteristicas() {
		return caracteristicas;
	}
	
	public Produto converter(EntityManager manager, Usuario usuario) {
		Categoria categoria = manager.find(Categoria.class, Long.valueOf(idCategoria));
		
		return new Produto(nome, valor, quantidade, descricao, categoria, usuario, caracteristicas);
		
	}
	
	
	
}
