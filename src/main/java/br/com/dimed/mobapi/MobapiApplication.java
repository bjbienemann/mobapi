package br.com.dimed.mobapi;

import java.nio.charset.StandardCharsets;
import java.util.Collections;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Brian Bienemann
 *
 *{@link http://tutorials.jenkov.com/java-json/jackson-objectmapper.html#how-jackson-objectmapper-matches-json-fields-to-java-fields}
 */

@SpringBootApplication
public class MobapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobapiApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
		converter.setSupportedMediaTypes(Collections.singletonList(MediaType.TEXT_HTML));
		converter.setDefaultCharset(StandardCharsets.UTF_8);

		return builder.additionalMessageConverters(converter).build();
	}

}
