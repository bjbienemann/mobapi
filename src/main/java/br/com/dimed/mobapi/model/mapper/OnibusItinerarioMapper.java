package br.com.dimed.mobapi.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.dimed.mobapi.model.dto.OnibusItinerarioDto;
import br.com.dimed.mobapi.model.entity.OnibusItinerarioEntity;
import br.com.poatransporte.model.Itinerario;

@Mapper(componentModel="spring")
public interface OnibusItinerarioMapper {
	
	@Mapping(source = "idLinha", target = "idOnibusLinha")
	OnibusItinerarioEntity toEntity(OnibusItinerarioDto dto);
	
	@Mapping(source = "idOnibusLinha", target = "idLinha")
	OnibusItinerarioDto toDto(OnibusItinerarioEntity entity);
	
	OnibusItinerarioEntity itinerarioToEntity(Itinerario itinerario);
	
}
