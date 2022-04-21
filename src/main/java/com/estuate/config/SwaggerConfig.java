package com.estuate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static springfox.documentation.builders.PathSelectors.regex;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket postApi() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("employee")
				.apiInfo(apiInfo()).select().paths(regex("/emp.*")).build();
				
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Employee Service").description("Sample Documentation")
				.termsOfServiceUrl("https://www.youtube.com").license("java Employee License").licenseUrl("https://www.youtube.com")
				.version("1.0").build();
	}
}
