package br.com.dimed.mobapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import br.com.dimed.mobapi.model.entity.OnibusItinerarioEntity;

public interface OnibusItinerarioRepository extends CrudRepository<OnibusItinerarioEntity, Long> {

	Page<OnibusItinerarioEntity> findByidOnibusLinha(Long idOnibusLinha, Pageable pageable);

}
