package com.faros.gestionusuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
@Schema(description = "Información para registrar o actualizar un usuario")
public class UsuarioRequest {

    @Schema(
            description = "Nombre completo",
            example = "Juan Pérez"
    )
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Schema(
            description = "Correo electrónico",
            example = "juan@faros.com"
    )
    @Email(message = "Debe ingresar un correo válido")
    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @Schema(
            description = "Contraseña",
            example = "Admin1234"
    )
    @NotBlank(message = "La contraseña es obligatoria")
    @Size(
            min = 8,
            max = 50,
            message = "La contraseña debe tener entre 8 y 50 caracteres"
    )
    private String password;

    @Schema(
            description = "Rol del usuario",
            example = "ADMIN"
    )
    @NotBlank(message = "El rol es obligatorio")
    private String rol;

}