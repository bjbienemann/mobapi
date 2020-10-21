package br.com.dimed.mobapi.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;

public class TaxiDto {
	
	@NotBlank
	@CsvBindByName(column = "NOME_DO_PONTO")
	private String nomePonto;
	
	@NotNull
	@CsvBindByName(column = "LATITUDE")
	private BigDecimal latitude;
	
	@NotNull
	@CsvBindByName(column = "LONGITUDE")
	private BigDecimal longitude;
	
	@CsvDate("yyyy-MM-dd'T'HH:mm:ss.SSS")
	@CsvBindByName(column = "DATA_HORA_CADASTRO")
	private LocalDateTime dataHora;

	public String getNomePonto() {
		return nomePonto;
	}

	public void setNomePonto(String nomePonto) {
		this.nomePonto = nomePonto;
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

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nomePonto == null) ? 0 : nomePonto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		TaxiDto other = (TaxiDto) obj;
		if (nomePonto == null) {
			if (other.nomePonto != null) {
				return false;
			}
		} else if (!nomePonto.equals(other.nomePonto)) {
			return false;
		}
		return true;
	}

}
