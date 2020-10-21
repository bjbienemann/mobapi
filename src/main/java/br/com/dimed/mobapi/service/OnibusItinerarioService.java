package br.com.dimed.mobapi.service;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.dimed.mobapi.exception.NenhumRegistroEncotradoException;
import br.com.dimed.mobapi.model.dto.OnibusItinerarioDto;
import br.com.dimed.mobapi.model.entity.OnibusItinerarioEntity;
import br.com.dimed.mobapi.model.filter.OnibusItinerarioFilter;
import br.com.dimed.mobapi.model.mapper.OnibusItinerarioMapper;
import br.com.dimed.mobapi.repository.OnibusItinerarioRepository;

@Service
public class OnibusItinerarioService {
	
	@Autowired
	private OnibusItinerarioRepository onibusItinerarioRepository;
	
	@Autowired
	private OnibusItinerarioMapper onibusItinerarioMapper;
	
	public Page<OnibusItinerarioDto> filtrar(OnibusItinerarioFilter filter, Pageable pageable) {
		if (Objects.nonNull(filter) && Objects.nonNull(filter.getIdLinha())) {
			Page<OnibusItinerarioEntity> entities = onibusItinerarioRepository
					.findByidOnibusLinha(filter.getIdLinha(), pageable);
			
			return entities.map(onibusItinerarioMapper::toDto);
		}
		
		throw new NenhumRegistroEncotradoException();
	}
	
	public OnibusItinerarioDto salvar(OnibusItinerarioDto dto) {
		var onibusItinerario = onibusItinerarioMapper.toEntity(dto);
		onibusItinerario = onibusItinerarioRepository.save(onibusItinerario);
		
		return onibusItinerarioMapper.toDto(onibusItinerario);
	}

	public OnibusItinerarioDto salvar(Long id, OnibusItinerarioDto dto) {
		var onibusItinerario = onibusItinerarioRepository.findById(id)
				.orElseThrow(() ->  new NenhumRegistroEncotradoException());
		
		var onibusItinerarioAtualizado = onibusItinerarioMapper.toEntity(dto);
		BeanUtils.copyProperties(onibusItinerarioAtualizado, onibusItinerario, "id");
		onibusItinerario = onibusItinerarioRepository.save(onibusItinerario);
		
		return onibusItinerarioMapper.toDto(onibusItinerario);
	}

	public void deletar(Long id) {
		onibusItinerarioRepository.deleteById(id);
	}
}
