package com.faros.gestionusuarios.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import lombok.Data;

@Data
@Schema(description = "Información del usuario")
public class UsuarioResponse {

    @Schema(example = "1")
    private Long id;

    @Schema(example = "Juan Pérez")
    private String nombre;

    @Schema(example = "juan@faros.com")
    private String correo;

    @Schema(example = "ADMIN")
    private String rol;

    @Schema(example = "true")
    private Boolean estado;

}