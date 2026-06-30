package com.faros.gestionusuarios.dto;

import lombok.Data;

@Data

public class UsuarioResponse {

    private Long id;

    private String nombre;

    private String correo;

    private String rol;

    private Boolean estado;

}