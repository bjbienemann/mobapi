package br.com.dimed.mobapi.integracao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.orm.jpa.JpaSystemException;

import br.com.dimed.mobapi.model.dto.OnibusLinhaDto;
import br.com.dimed.mobapi.service.OnibusLinhaService;

@SpringBootTest
public class OnibusLinhaIT {
	
	@Autowired
	private OnibusLinhaService onibusLinhaService;
	
	@Test
	public void inserirUmNovoRegistro() {
		var dto = new OnibusLinhaDto();
		dto.setId(123456L);
		dto.setCodigo("B10-1");
		dto.setNome("CIDADE ALTA");
		
		var dtoSalvo = onibusLinhaService.salvar(dto);
		
		assertThat(dtoSalvo).isNotNull();
	}
	
	@Test
	public void inserirUmNovoRegistroSemId() {
		var dto = new OnibusLinhaDto();
		dto.setCodigo("B10-2");
		dto.setNome("CIDADE ALTA");
		
		assertThrows(JpaSystemException.class,() -> {
			onibusLinhaService.salvar(dto);
		});
	}
	
	@Test
	public void inserirUmNovoRegistroSemCodigo() {
		var dto = new OnibusLinhaDto();
		dto.setId(123456L);
		dto.setCodigo(null);
		dto.setNome("CIDADE ALTA");
		
		assertThrows(DataIntegrityViolationException.class,() -> {
			onibusLinhaService.salvar(dto);
		});
	}
	
	@Test
	public void deletarUmRegistroInexistente() {
		assertThrows(EmptyResultDataAccessException.class, () -> onibusLinhaService.deletar(999999L));
	}
	
}
