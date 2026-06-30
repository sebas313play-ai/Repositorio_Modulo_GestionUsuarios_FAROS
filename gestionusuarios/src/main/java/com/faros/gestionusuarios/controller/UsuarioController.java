package com.faros.gestionusuarios.controller;

import com.faros.gestionusuarios.dto.*;

import com.faros.gestionusuarios.service.UsuarioService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/usuarios")

@RequiredArgsConstructor

public class UsuarioController {

    private final UsuarioService service;

    @PostMapping

    public UsuarioResponse guardar(

            @RequestBody UsuarioRequest request){

        return service.guardar(request);

    }

    @GetMapping

    public List<UsuarioResponse> listar(){

        return service.listar();

    }

    @GetMapping("/{id}")

    public UsuarioResponse buscar(

            @PathVariable Long id){

        return service.buscar(id);

    }

    @PutMapping("/{id}")

    public UsuarioResponse actualizar(

            @PathVariable Long id,

            @RequestBody UsuarioRequest request){

        return service.actualizar(id,request);

    }

    @DeleteMapping("/{id}")

    public void eliminar(

            @PathVariable Long id){

        service.eliminar(id);

    }

}