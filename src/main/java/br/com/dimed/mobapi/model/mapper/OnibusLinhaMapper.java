package br.com.dimed.mobapi.model.mapper;

import org.mapstruct.Mapper;

import br.com.dimed.mobapi.model.dto.OnibusLinhaDto;
import br.com.dimed.mobapi.model.entity.OnibusLinhaEntity;
import br.com.poatransporte.model.LinhaOnibus;

@Mapper(componentModel="spring")
public interface OnibusLinhaMapper {
	
	OnibusLinhaEntity toEntity(OnibusLinhaDto dto);
	OnibusLinhaDto toDto(OnibusLinhaEntity entity);
	
	OnibusLinhaEntity linhaOnibusToEntity(LinhaOnibus linhaOnibus);
	
}
