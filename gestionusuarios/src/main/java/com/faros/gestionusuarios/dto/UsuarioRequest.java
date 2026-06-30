package com.faros.gestionusuarios.dto;

import lombok.Data;

@Data

public class UsuarioRequest {

    private String nombre;

    private String correo;

    private String password;

    private String rol;

}