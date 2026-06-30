package com.faros.gestionusuarios.config;

import io.swagger.v3.oas.models.OpenAPI;

import io.swagger.v3.oas.models.info.Info;

import org.springframework.context.annotation.*;

@Configuration

public class OpenApiConfig {

    @Bean

    public OpenAPI farosApi(){

        return new OpenAPI()

                .info(

                        new Info()

                                .title("FAROS")

                                .description(
                                        "Gestión de Usuarios"
                                )

                                .version("1.0")

                );

    }

}