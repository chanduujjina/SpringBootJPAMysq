package com.practice;

import static springfox.documentation.builders.PathSelectors.regex;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootFeaturesApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootFeaturesApplication.class, args);
	}
	
	@Bean
	public Docket getEmployeeInfo() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.practice"))
				.paths(regex("/empDetails.*")).build().apiInfo(apiInfo());
	}
	
	private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot REST API","Rest api for employee services","","","","","");
        return apiInfo;
    }

}
