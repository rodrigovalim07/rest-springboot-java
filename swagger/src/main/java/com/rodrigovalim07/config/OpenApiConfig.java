package com.rodrigovalim07.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
				.title("RESTful API with Java 18 and Spring Boot 3")
				.version("v1")
				.description("Some description about my API")
				.termsOfService("https://rodrigo.com.br/termos")
				.license(new License().name("Apache 2.0")
				.url("https://rodrigo.com.br/meus-cursos")));
	}
}
