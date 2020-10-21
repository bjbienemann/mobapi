package br.com.dimed.mobapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.mobapi.model.dto.OnibusItinerarioDto;
import br.com.dimed.mobapi.model.filter.OnibusItinerarioFilter;
import br.com.dimed.mobapi.service.OnibusItinerarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Ônibus")
@RestController
@RequestMapping("/onibus/itinerarios")
public class OnibusItinerarioController {
	
	@Autowired
	private OnibusItinerarioService onibusItinerarioService;
	
	@GetMapping
	@Operation(summary = "Buscar por itinerários", description = "Buscar por itinerários de unidades de transporte")
	public Page<OnibusItinerarioDto> buscar(@Valid OnibusItinerarioFilter filter, Pageable pageable) {
		return onibusItinerarioService.filtrar(filter, pageable);
	}
	
	@PostMapping
	@Operation(summary = "Inserir um novo itinerário")
	public ResponseEntity<OnibusItinerarioDto> inserir(@Valid @RequestBody OnibusItinerarioDto dto) {
		var onibusItinerario = onibusItinerarioService.salvar(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(onibusItinerario);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar um itinerário por ID")
	public ResponseEntity<OnibusItinerarioDto> atualizar(Long id, @Valid @RequestBody OnibusItinerarioDto dto) {
		var onibusItinerario = onibusItinerarioService.salvar(id, dto);
		
		return ResponseEntity.ok(onibusItinerario);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar um itinerário por ID")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(Long id) {
		onibusItinerarioService.deletar(id);
	}
}
