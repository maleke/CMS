package com.digipay.cardmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("full-petstore-api")
                .apiInfo(apiInfo())
                .select()
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Card management system API")
                .description(
                        "api documentation for card management system")
                .termsOfServiceUrl("http://springfox.io")
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }
}