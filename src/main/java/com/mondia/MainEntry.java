package com.mondia;

import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.shell.CommandLine;
import org.springframework.shell.SimpleShellCommandLineOptions;
import org.springframework.shell.core.ExitShellRequest;
import org.springframework.shell.core.JLineShellComponent;
import org.springframework.util.StopWatch;

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

		final ConfigurableApplicationContext run = SpringApplication.run(MainEntry.class);
		try {
			final BootShim bs = new BootShim(args, run);
			bs.run();
		} catch (final RuntimeException e) {
			throw e;
		}
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build();
	}

	// swagger
	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Spring Boot Aaxon REST Sample with Swagger")
				.description("Spring Boot Aaxon REST Sample with Swagger").contact("Sravan Kumar")
				.license("Apache License Version 2.0").build();
	}

}
