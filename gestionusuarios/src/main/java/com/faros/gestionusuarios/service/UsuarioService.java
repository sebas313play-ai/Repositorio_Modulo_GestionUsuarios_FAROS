package com.faros.gestionusuarios.service;

import com.faros.gestionusuarios.dto.*;

import java.util.List;

public interface UsuarioService {

    UsuarioResponse guardar(UsuarioRequest request);

    List<UsuarioResponse> listar();

    UsuarioResponse buscar(Long id);

    UsuarioResponse actualizar(Long id,
            UsuarioRequest request);

    void eliminar(Long id);

}