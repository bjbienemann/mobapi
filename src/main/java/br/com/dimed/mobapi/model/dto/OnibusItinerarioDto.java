package br.com.dimed.mobapi.model.dto;

import java.math.BigDecimal;

public class OnibusItinerarioDto {
	
	private Long idLinha;
	private BigDecimal latitude;
	private BigDecimal longitude;

	public Long getIdLinha() {
		return idLinha;
	}

	public void setIdLinha(Long idLinha) {
		this.idLinha = idLinha;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}
	
}
