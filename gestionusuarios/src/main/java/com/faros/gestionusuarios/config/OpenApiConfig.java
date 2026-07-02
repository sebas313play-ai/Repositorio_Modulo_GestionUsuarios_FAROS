package com.faros.gestionusuarios.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI farosApi() {

        final String securitySchemeName = "bearerAuth";

        return new OpenAPI()

                .info(new Info()

                        .title("FAROS - Gestión de Usuarios")

                        .version("1.0")

                        .description("API REST para la gestión de usuarios.")

                        .contact(new Contact()

                                .name("Equipo FAROS")

                                .email("faros@correo.com")
                        )
                )

                .addSecurityItem(

                        new SecurityRequirement()

                                .addList(securitySchemeName)
                )

                .schemaRequirement(

                        securitySchemeName,

                        new SecurityScheme()

                                .name("Authorization")

                                .type(SecurityScheme.Type.HTTP)

                                .scheme("bearer")

                                .bearerFormat("JWT")
                );

    }

}