package br.com.dimed.mobapi.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.dimed.mobapi.model.entity.OnibusItinerarioEntity;
import br.com.dimed.mobapi.model.entity.OnibusLinhaEntity;
import br.com.dimed.mobapi.model.mapper.OnibusItinerarioMapper;
import br.com.dimed.mobapi.model.mapper.OnibusLinhaMapper;
import br.com.dimed.mobapi.repository.OnibusItinerarioRepository;
import br.com.dimed.mobapi.repository.OnibusLinhaRepository;
import br.com.poatransporte.model.Itinerario;
import br.com.poatransporte.model.ItinerarioDeserializer;
import br.com.poatransporte.model.LinhaOnibus;

@Service
public class IntegracaoService {

	private static final Logger log = LoggerFactory.getLogger(IntegracaoService.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private OnibusLinhaRepository onibusLinhaRepository;

	@Autowired
	private OnibusItinerarioRepository onibusItinerarioRepository;

	@Autowired
	private OnibusLinhaMapper onibusLinhaMapper;

	@Autowired
	private OnibusItinerarioMapper onibusItinerarioMapper;

	public void importar() {
		if (onibusLinhaRepository.count() == 0) {
			long startTimeMillis = System.currentTimeMillis();
			log.info("INICIO Importação das linhas de ônibus");

			List<LinhaOnibus> linhasOnibus = importarLinhas();
			saveAllOnibusLinhas(linhasOnibus);

			long stopTimeMillis = System.currentTimeMillis();
			log.info("FIM Importação das linhas de ônibus [{}]", (stopTimeMillis - startTimeMillis));

			startTimeMillis = System.currentTimeMillis();
			log.info("INICIO Importação dos itinerários por unidade de transporte");

			for (int i = 0; i < linhasOnibus.size(); i++) {
				List<OnibusItinerarioEntity> entities = importarItinerariosPorLinha(linhasOnibus.get(i));

				onibusItinerarioRepository.saveAll(entities);
			}

			stopTimeMillis = System.currentTimeMillis();
			log.info("FIM Importação dos itinerários por unidade de transporte [{}]", (stopTimeMillis - startTimeMillis));
		}
	}

	private void saveAllOnibusLinhas(List<LinhaOnibus> linhasOnibus) {
		List<OnibusLinhaEntity> entities = linhasOnibus.stream().map(onibusLinhaMapper::linhaOnibusToEntity)
				.collect(Collectors.toList());

		onibusLinhaRepository.saveAll(entities);
	}

	public List<LinhaOnibus> importarLinhas() {
		var url = "http://www.poatransporte.com.br/php/facades/process.php?a=nc&p=%&t=o";

		ResponseEntity<LinhaOnibus[]> response = restTemplate.getForEntity(url, LinhaOnibus[].class);

		return Arrays.asList(response.getBody());
	}

	public List<OnibusItinerarioEntity> importarItinerariosPorLinha(LinhaOnibus linhaOnibus) {
		try {
			List<Itinerario> itinerarios = importarItinerarios(linhaOnibus);
			List<OnibusItinerarioEntity> entities = itinerarios.stream()
					.map(itinerario -> novoOnibusItinerarioEntity(linhaOnibus.getId(), itinerario))
					.collect(Collectors.toList());

			return entities;
		} catch (JsonProcessingException e) {
			log.error("Ocorreu um erro ao importar itinerário por linha", e);
		}

		return new ArrayList<>();
	}

	private OnibusItinerarioEntity novoOnibusItinerarioEntity(Long idOnibusLinha, Itinerario itinerario) {
		OnibusItinerarioEntity entity = onibusItinerarioMapper.itinerarioToEntity(itinerario);
		entity.setIdOnibusLinha(idOnibusLinha);

		return entity;
	}

	public List<Itinerario> importarItinerarios(LinhaOnibus linhaOnibus) throws JsonProcessingException {
		var url = "http://www.poatransporte.com.br/php/facades/process.php?a=il&p="
				.concat(linhaOnibus.getId().toString());

		ResponseEntity<String> resp = restTemplate.getForEntity(url, String.class);

		String jsonData = resp.getBody();

		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addDeserializer(Itinerario[].class, new ItinerarioDeserializer(Itinerario[].class));

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(simpleModule);

		Itinerario[] itinerarios = objectMapper.readValue(jsonData, Itinerario[].class);
		return Arrays.asList(itinerarios);
	}

}
