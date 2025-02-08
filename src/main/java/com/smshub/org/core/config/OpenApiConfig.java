package com.smshub.org.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SMSHUB API")
                        .version("1.0")
                        .description("Plateforme SAAS permettant d'envoyer des SMS")
                        .contact(new Contact()
                                .name("DIGITALIS")
                                .email("contac@smshub.com")));
    }
}