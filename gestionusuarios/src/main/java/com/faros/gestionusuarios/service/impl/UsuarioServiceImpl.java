package com.faros.gestionusuarios.service.impl;

import com.faros.gestionusuarios.dto.UsuarioRequest;
import com.faros.gestionusuarios.dto.UsuarioResponse;
import com.faros.gestionusuarios.entity.Usuario;
import com.faros.gestionusuarios.exception.ResourceNotFoundException;
import com.faros.gestionusuarios.repository.UsuarioRepository;
import com.faros.gestionusuarios.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UsuarioResponse guardar(UsuarioRequest request) {

        if (repository.existsByCorreo(request.getCorreo())) {

            throw new RuntimeException("El correo ya se encuentra registrado.");

        }

        Usuario usuario = Usuario.builder()

                .nombre(request.getNombre())

                .correo(request.getCorreo())

                .password(passwordEncoder.encode(request.getPassword()))

                .rol(request.getRol())

                .estado(true)

                .build();

        repository.save(usuario);

        return convertir(usuario);

    }

    @Override
    public List<UsuarioResponse> listar() {

        return repository.findAll()

                .stream()

                .map(this::convertir)

                .collect(Collectors.toList());

    }

    @Override
    public UsuarioResponse buscar(Long id) {

        Usuario usuario = repository.findById(id)

                .orElseThrow(() ->

                        new ResourceNotFoundException("Usuario no encontrado."));

        return convertir(usuario);

    }

    @Override
    public UsuarioResponse actualizar(

            Long id,

            UsuarioRequest request) {

        Usuario usuario = repository.findById(id)

                .orElseThrow(() ->

                        new ResourceNotFoundException("Usuario no encontrado."));

        usuario.setNombre(request.getNombre());

        usuario.setCorreo(request.getCorreo());

        usuario.setPassword(

                passwordEncoder.encode(request.getPassword())

        );

        usuario.setRol(request.getRol());

        repository.save(usuario);

        return convertir(usuario);

    }

    @Override
    public void eliminar(Long id) {

        Usuario usuario = repository.findById(id)

                .orElseThrow(() ->

                        new ResourceNotFoundException("Usuario no encontrado."));

        repository.delete(usuario);

    }

    private UsuarioResponse convertir(Usuario usuario) {

        UsuarioResponse response = new UsuarioResponse();

        response.setId(usuario.getId());

        response.setNombre(usuario.getNombre());

        response.setCorreo(usuario.getCorreo());

        response.setRol(usuario.getRol());

        response.setEstado(usuario.getEstado());

        return response;

    }

}