package br.com.dimed.mobapi.controller;

import java.util.List;

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

import br.com.dimed.mobapi.model.dto.OnibusLinhaDto;
import br.com.dimed.mobapi.model.filter.OnibusLinhaFilter;
import br.com.dimed.mobapi.model.filter.RaioItinerarioFilter;
import br.com.dimed.mobapi.service.OnibusLinhaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Ônibus")
@RestController
@RequestMapping("/onibus/linhas")
public class OnibusLinhaController {
	
	@Autowired
	private OnibusLinhaService onibusLinhaService;
	
	@GetMapping
	@Operation(summary = "Buscar por linhas", description = "Buscar por linhas de ônibus")
	public Page<OnibusLinhaDto> buscar(@Valid OnibusLinhaFilter filter, Pageable pageable) {
		return onibusLinhaService.filtrar(filter, pageable);
	}
	
	@PostMapping
	@Operation(summary = "Inserir uma nova linha")
	public ResponseEntity<OnibusLinhaDto> inserir(@Valid @RequestBody OnibusLinhaDto dto) {
		var onibusLinha = onibusLinhaService.salvar(dto);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(onibusLinha);
	}
	
	@PutMapping("/{id}")
	@Operation(summary = "Atualizar uma linha por ID")
	public ResponseEntity<OnibusLinhaDto> atualizar(Long id, @Valid @RequestBody OnibusLinhaDto dto) {
		var onibusLinha = onibusLinhaService.salvar(id, dto);
		
		return ResponseEntity.ok(onibusLinha);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar uma linha por ID")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deletar(Long id) {
		onibusLinhaService.deletar(id);
	}
	
	@GetMapping("/por/raio")
	@Operation(summary = "Buscar linhas de ônibus por raio.", description = "passando uma" + 
			"latitude, longitude e um raio em KM, trazer todas as linhas dentro do raio informado.")
	public ResponseEntity<List<OnibusLinhaDto>> buscarLinhaPorRaio(@Valid RaioItinerarioFilter filter) {
		var linhas = onibusLinhaService.filtrarLinahasPorRaio(filter);
		
		return ResponseEntity.ok(linhas);
	}
}
