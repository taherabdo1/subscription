package com.mondia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = { "com.mondia.resources" })
public class MainEntry {

	public static void main(String[] args) {
		SpringApplication.run(MainEntry.class, args);
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Boot Aaxon REST Sample with Swagger")
				.description("Spring Boot Aaxon REST Sample with Swagger").contact("Sravan Kumar")
				.license("Apache License Version 2.0").build();
	}
}
