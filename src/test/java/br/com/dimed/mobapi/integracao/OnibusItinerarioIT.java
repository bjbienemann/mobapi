package br.com.dimed.mobapi.integracao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import br.com.dimed.mobapi.exception.NenhumRegistroEncotradoException;
import br.com.dimed.mobapi.model.dto.OnibusItinerarioDto;
import br.com.dimed.mobapi.service.OnibusItinerarioService;

@SpringBootTest
public class OnibusItinerarioIT {
	
	@Autowired
	private OnibusItinerarioService onibusItinerarioService;
	
	@Test
	public void inserirUmNovoRegistro() {
		var dto = new OnibusItinerarioDto();
		dto.setIdLinha(123456L);
		dto.setOrdem(999999);
		dto.setLatitude(new BigDecimal(-29.98238458977900000));
		dto.setLongitude(new BigDecimal(-51.20074322150500000));
		
		var dtoSalvo = onibusItinerarioService.salvar(dto);
		
		assertThat(dtoSalvo).isNotNull();
	}
	
	@Test
	public void alterarUmNovoRegistro() {
		var dto = new OnibusItinerarioDto();
		dto.setIdLinha(123456L);
		dto.setOrdem(999999);
		dto.setLatitude(new BigDecimal(-29.98238458977900000));
		dto.setLongitude(new BigDecimal(-51.20074322150500000));
		
		assertThrows(NenhumRegistroEncotradoException.class,() -> {
			onibusItinerarioService.salvar(1999999L, dto);
		});
	}
	
	@Test
	public void inserirUmNovoRegistroSemIdLinha() {
		var dto = new OnibusItinerarioDto();
		dto.setOrdem(999999);
		dto.setLatitude(new BigDecimal(-29.98238458977900000));
		dto.setLongitude(new BigDecimal(-51.20074322150500000));
		
		assertThrows(DataIntegrityViolationException.class,() -> {
			onibusItinerarioService.salvar(dto);
		});
	}
	
	@Test
	public void deletarUmRegistroInexistente() {
		assertThrows(EmptyResultDataAccessException.class, () -> onibusItinerarioService.deletar(999999L));
	}
	
}
