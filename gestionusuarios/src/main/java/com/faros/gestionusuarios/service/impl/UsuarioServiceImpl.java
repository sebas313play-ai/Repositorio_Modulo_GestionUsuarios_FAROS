package com.faros.gestionusuarios.service.impl;

import com.faros.gestionusuarios.dto.*;
import com.faros.gestionusuarios.entity.Usuario;
import com.faros.gestionusuarios.repository.UsuarioRepository;
import com.faros.gestionusuarios.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

@RequiredArgsConstructor

public class UsuarioServiceImpl
        implements UsuarioService {

    private final UsuarioRepository repository;

    @Override

    public UsuarioResponse guardar(UsuarioRequest request) {

        Usuario usuario = Usuario.builder()

                .nombre(request.getNombre())

                .correo(request.getCorreo())

                .password(request.getPassword())

                .rol(request.getRol())

                .estado(true)

                .build();

        repository.save(usuario);

        UsuarioResponse response = new UsuarioResponse();

        response.setId(usuario.getId());

        response.setNombre(usuario.getNombre());

        response.setCorreo(usuario.getCorreo());

        response.setRol(usuario.getRol());

        response.setEstado(usuario.getEstado());

        return response;

    }

    @Override

    public List<UsuarioResponse> listar() {

        return repository.findAll()

                .stream()

                .map(usuario -> {

                    UsuarioResponse r = new UsuarioResponse();

                    r.setId(usuario.getId());

                    r.setNombre(usuario.getNombre());

                    r.setCorreo(usuario.getCorreo());

                    r.setRol(usuario.getRol());

                    r.setEstado(usuario.getEstado());

                    return r;

                }).collect(Collectors.toList());

    }

    @Override

    public UsuarioResponse buscar(Long id) {

        Usuario usuario = repository.findById(id).orElseThrow();

        UsuarioResponse response = new UsuarioResponse();

        response.setId(usuario.getId());

        response.setNombre(usuario.getNombre());

        response.setCorreo(usuario.getCorreo());

        response.setRol(usuario.getRol());

        response.setEstado(usuario.getEstado());

        return response;

    }

    @Override

    public UsuarioResponse actualizar(Long id,
            UsuarioRequest request) {

        Usuario usuario = repository.findById(id).orElseThrow();

        usuario.setNombre(request.getNombre());

        usuario.setCorreo(request.getCorreo());

        usuario.setPassword(request.getPassword());

        usuario.setRol(request.getRol());

        repository.save(usuario);

        return buscar(id);

    }

    @Override

    public void eliminar(Long id) {

        repository.deleteById(id);

    }

}