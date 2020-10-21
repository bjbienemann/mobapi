package br.com.dimed.mobapi.config;

import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI(BuildProperties buildProperties) {
		return new OpenAPI()
				.components(new Components())
				.info(new Info()
						.title(buildProperties.getName())
						.version(buildProperties.getVersion())
						.contact(new Contact()
								.name("Brian Bienemann")
								.email("bjbienemann@gmail.com")
								.url("https://www.linkedin.com/in/bjbienemann")));
	}

}
