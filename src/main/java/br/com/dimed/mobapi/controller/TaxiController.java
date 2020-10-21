package br.com.dimed.mobapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.dimed.mobapi.model.dto.TaxiDto;
import br.com.dimed.mobapi.service.TaxiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Taxi")
@RestController
@RequestMapping("/taxis/pontos")
public class TaxiController {
	
	@Autowired
	private TaxiService taxiService;
	
	@GetMapping
	@Operation(summary = "Buscar por pontos de taxis")
	public ResponseEntity<List<TaxiDto>> buscar(String nome) {
		var taxis = taxiService.filtrar(nome);
		
		return ResponseEntity.ok(taxis);
	}
	
	@PutMapping
	@Operation(summary = "Salvar um ponto de taxi")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void salvar(@Valid @RequestBody TaxiDto dto) {
		taxiService.salvar(dto);
	}
}
