package com.faros.gestionusuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Schema(description = "Credenciales para iniciar sesión")
public class LoginRequest {

    @Schema(
            description = "Correo del usuario",
            example = "admin@faros.com"
    )
    @Email(message = "Debe ingresar un correo válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @Schema(
            description = "Contraseña",
            example = "Admin1234"
    )
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

}