package br.com.dimed.mobapi.model.filter;

import javax.validation.constraints.NotBlank;

public class OnibusLinhaFilter {
	
	@NotBlank
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
