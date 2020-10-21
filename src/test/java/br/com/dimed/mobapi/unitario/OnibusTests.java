package br.com.dimed.mobapi.unitario;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import br.com.poatransporte.model.Itinerario;
import br.com.poatransporte.model.ItinerarioDeserializer;

public class OnibusTests {
	
	@Test
	public void deserializeItineratioTest() throws JsonMappingException, JsonProcessingException {
		String jsonData = "{\"idlinha\":\"5566\",\"nome\":\"VILA NOVA\",\"codigo\":\"266-1\","
				+ "\"0\":{\"lat\":\"-30.12419058977900000\",\"lng\":\"-51.22378322150500000\"},"
				+ "\"1\":{\"lat\":\"-30.12410058977900000\",\"lng\":\"-51.22352322150500000\"},"
				+ "\"2\":{\"lat\":\"-30.12373358977900000\",\"lng\":\"-51.22265722150500000\"},"
				+ "\"3\":{\"lat\":\"-30.12305758977900000\",\"lng\":\"-51.22116722150500000\"}}";

		SimpleModule simpleModule = new SimpleModule();
		simpleModule.addDeserializer(Itinerario[].class, new ItinerarioDeserializer(Itinerario[].class));

		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(simpleModule);
		
		Itinerario[] itinerarios = objectMapper.readValue(jsonData, Itinerario[].class);
		
		assertEquals(4, itinerarios.length);
	}
}
