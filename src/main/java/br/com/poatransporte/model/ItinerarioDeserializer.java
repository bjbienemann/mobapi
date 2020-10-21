package br.com.poatransporte.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


/**
 * 
 * @author Brian Bienemann
 * 
 *{@link http://tutorials.jenkov.com/java-json/jackson-objectmapper.html#custom-deserializer}
 */
public class ItinerarioDeserializer extends StdDeserializer<Itinerario[]> {
	
	private static final long serialVersionUID = 1L;
	
	public ItinerarioDeserializer(Class<?> vc) {
		super(vc);
	}

	@Override
	public Itinerario[] deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		List<Itinerario> itinerarios = new ArrayList<>();
		
		while (!p.isClosed()) {
			var jsonToken = p.nextToken();
			
			if (JsonToken.FIELD_NAME.equals(jsonToken)) {
				var fieldName = p.getCurrentName();

                jsonToken = p.nextToken();
                if (!"idlinha".equals(fieldName)
                		&& !"nome".equals(fieldName)
                		&& !"codigo".equals(fieldName)) {
                
                	if (JsonToken.START_OBJECT.equals(jsonToken)) {
                		ObjectMapper objectMapper = new ObjectMapper();
                		Itinerario itinerario = objectMapper.readValue(p, Itinerario.class);
                		itinerario.setOrdem(fieldName);
                		itinerarios.add(itinerario);
                	}                	
                }
			}
		}
		
		return itinerarios.toArray(new Itinerario[itinerarios.size()]);
	}

}
