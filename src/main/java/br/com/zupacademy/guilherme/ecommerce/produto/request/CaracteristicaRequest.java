package br.com.zupacademy.guilherme.ecommerce.produto.request;

import javax.validation.constraints.NotBlank;

import br.com.zupacademy.guilherme.ecommerce.produto.modelo.Caracteristica;
import br.com.zupacademy.guilherme.ecommerce.produto.modelo.Produto;

public class CaracteristicaRequest {
	
	@NotBlank
	private String nome;
	
	@NotBlank
	private String descricao;

	public CaracteristicaRequest(@NotBlank String nome, @NotBlank String descricao) {
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public Caracteristica converter(Produto produto) {
		return new Caracteristica(nome, descricao, produto);
	}
}
	