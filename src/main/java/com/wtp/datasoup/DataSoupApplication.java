package com.wtp.datasoup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

import static springfox.documentation.builders.PathSelectors.any;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
@EnableSwagger2
public class DataSoupApplication {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.wtp.datasoup")).paths(any()).build()
				.apiInfo(new ApiInfo("Data Soup",
						"Deep dive in price data",
						"1.0",
						null,
						new Contact("Liga", "github.com/ligaoz", "liga.ozolina@we-testpro.com"),
						null, null, new ArrayList()));
	}

	public static void main(String[] args) {
		SpringApplication.run(DataSoupApplication.class, args);
	}

}
