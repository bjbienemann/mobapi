package br.com.dimed.mobapi.model.filter;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class RaioItinerarioFilter {

	@NotNull
	private BigDecimal latitude;
	@NotNull
	private BigDecimal longitude;
	@NotNull
	private Long raio;

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

	public Long getRaio() {
		return raio;
	}

	public void setRaio(Long raio) {
		this.raio = raio;
	}

}
