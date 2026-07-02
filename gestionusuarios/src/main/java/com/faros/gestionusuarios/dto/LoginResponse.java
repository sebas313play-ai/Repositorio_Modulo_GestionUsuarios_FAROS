package com.faros.gestionusuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Respuesta del inicio de sesión")
public class LoginResponse {

    @Schema(
            description = "Token JWT generado",
            example = "eyJhbGciOiJIUzI1NiJ9..."
    )
    private String token;

}