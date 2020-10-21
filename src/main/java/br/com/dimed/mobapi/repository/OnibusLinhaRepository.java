package br.com.dimed.mobapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import br.com.dimed.mobapi.model.entity.OnibusLinhaEntity;

public interface OnibusLinhaRepository extends CrudRepository<OnibusLinhaEntity, Long> {

	Page<OnibusLinhaEntity> findByNomeContainingIgnoreCase(String nome, Pageable pageable);

}
