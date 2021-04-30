package br.com.zupacademy.guilherme.ecommerce.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import br.com.zupacademy.guilherme.ecommerce.shared.validators.ExistsId;
import br.com.zupacademy.guilherme.ecommerce.shared.validators.UniqueValue;

public class CategoriaRequest {
	
	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome")
	private String nome;
	
	@ExistsId(domainClass = Categoria.class, fieldName = "id")
	private String idCategoriaMae;

	
	public CategoriaRequest(@NotBlank String nome, String idCategoriaMae) {
		this.nome = nome;
		this.idCategoriaMae = idCategoriaMae;
	}
	
	public String getIdCategoriaMae() {
		return idCategoriaMae;
	}
	
	public Categoria converter(EntityManager manager) {
		Categoria categoria = new Categoria(this.nome);
		if(idCategoriaMae != null) {
			Categoria categoriaMae = manager.find(Categoria.class, Long.valueOf(idCategoriaMae));
			Assert.notNull(categoriaMae, "O id da categoria mae precisa ser válido");
			categoria.setCategoriaMae(categoriaMae);	
		}
		return categoria;
	}
}
