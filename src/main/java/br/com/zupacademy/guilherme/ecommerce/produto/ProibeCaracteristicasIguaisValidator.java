package br.com.zupacademy.guilherme.ecommerce.produto;

import java.util.HashSet;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.zupacademy.guilherme.ecommerce.produto.request.CaracteristicaRequest;

public class ProibeCaracteristicasIguaisValidator implements ConstraintValidator<ProibeCaracteristicasIguais, List<CaracteristicaRequest>> {

    //private String message;
	
	@Override
	public void initialize(ProibeCaracteristicasIguais params) {
	}
	
    @Override
    public boolean isValid(List<CaracteristicaRequest> value, ConstraintValidatorContext constraintContext) {
    	HashSet<String> nomesIguais = new HashSet<>();
    	for(CaracteristicaRequest caracteristica : value) {
    		if(!nomesIguais.add(caracteristica.getNome())) {
    			return false;
    		}
    	}
    	return true;
    }
}