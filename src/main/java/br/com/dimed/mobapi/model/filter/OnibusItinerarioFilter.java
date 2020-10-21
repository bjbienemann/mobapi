package br.com.dimed.mobapi.model.filter;

import javax.validation.constraints.NotNull;

public class OnibusItinerarioFilter {
	
	@NotNull
	private Long idLinha;

	public Long getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(Long idLinha) {
		this.idLinha = idLinha;
	}

}
