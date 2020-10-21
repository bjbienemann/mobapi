package br.com.poatransporte.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Itinerario {

	private String ordem;
	@JsonProperty("lat")
	private String latitude;
	@JsonProperty("lng")
	private String longitude;

	public String getOrdem() {
		return ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

}
