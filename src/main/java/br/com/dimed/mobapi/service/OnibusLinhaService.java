package br.com.dimed.mobapi.service;

import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.dimed.mobapi.exception.NenhumRegistroEncotradoException;
import br.com.dimed.mobapi.model.dto.OnibusLinhaDto;
import br.com.dimed.mobapi.model.entity.OnibusLinhaEntity;
import br.com.dimed.mobapi.model.filter.OnibusLinhaFilter;
import br.com.dimed.mobapi.model.filter.RaioItinerarioFilter;
import br.com.dimed.mobapi.model.mapper.OnibusLinhaMapper;
import br.com.dimed.mobapi.repository.OnibusJdbcTemplate;
import br.com.dimed.mobapi.repository.OnibusLinhaRepository;

@Service
public class OnibusLinhaService {
	
	@Autowired
	private OnibusLinhaRepository onibusLinhaRepository;
	
	@Autowired
	private OnibusJdbcTemplate onibusJdbcTemplate;
	
	@Autowired
	private OnibusLinhaMapper onibusLinhaMapper;
	
	public Page<OnibusLinhaDto> filtrarLinahasPorRaio(RaioItinerarioFilter filter, Pageable pageable) {
		return onibusJdbcTemplate.findByRaio(filter, pageable);
	}
	
	public Page<OnibusLinhaDto> filtrar(OnibusLinhaFilter filter, Pageable pageable) {
		if (Objects.nonNull(filter) 
				&& Objects.nonNull(filter.getNome())
				&& !filter.getNome().isEmpty()) {
			
			Page<OnibusLinhaEntity> entities = onibusLinhaRepository
					.findByNomeContainingIgnoreCase(filter.getNome(), pageable);
			
			return entities.map(onibusLinhaMapper::toDto);
		}
		
		throw new NenhumRegistroEncotradoException();
	}
	
	public OnibusLinhaDto salvar(OnibusLinhaDto dto) {
		var onibusLinha = onibusLinhaMapper.toEntity(dto);
		onibusLinha = onibusLinhaRepository.save(onibusLinha);
		
		return onibusLinhaMapper.toDto(onibusLinha);
	}

	public OnibusLinhaDto salvar(Long id, OnibusLinhaDto dto) {
		var onibusLinha = onibusLinhaRepository.findById(id)
				.orElseThrow(() ->  new NenhumRegistroEncotradoException());
		
		var onibusLinhaAtualizado = onibusLinhaMapper.toEntity(dto);
		BeanUtils.copyProperties(onibusLinhaAtualizado, onibusLinha, "id");
		onibusLinha = onibusLinhaRepository.save(onibusLinha);
		
		return onibusLinhaMapper.toDto(onibusLinha);
	}

	public void deletar(Long id) {
		onibusLinhaRepository.deleteById(id);
	}

	public OnibusLinhaDto buscarPorId(Long id) {
		var entity = onibusLinhaRepository.findById(id)
				.orElseThrow(() ->  new NenhumRegistroEncotradoException());
		
		return onibusLinhaMapper.toDto(entity);
	}
}
