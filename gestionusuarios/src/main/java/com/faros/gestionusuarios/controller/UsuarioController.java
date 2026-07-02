package com.faros.gestionusuarios.controller;

import com.faros.gestionusuarios.dto.UsuarioRequest;
import com.faros.gestionusuarios.dto.UsuarioResponse;
import com.faros.gestionusuarios.service.UsuarioService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
@Tag(
        name = "Usuarios",
        description = "Gestión de usuarios del sistema FAROS"
)
@SecurityRequirement(name = "bearerAuth")
public class UsuarioController {

    private final UsuarioService service;

    @Operation(summary = "Registrar un usuario")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuario registrado correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos inválidos"),
            @ApiResponse(responseCode = "401", description = "No autorizado")
    })
    @PostMapping
    public UsuarioResponse guardar(
            @Valid
            @RequestBody UsuarioRequest request) {

        return service.guardar(request);
    }

    @Operation(summary = "Listar todos los usuarios")
    @GetMapping
    public List<UsuarioResponse> listar() {

        return service.listar();
    }

    @Operation(summary = "Buscar usuario por ID")
    @GetMapping("/{id}")
    public UsuarioResponse buscar(
            @PathVariable Long id) {

        return service.buscar(id);
    }

    @Operation(summary = "Actualizar usuario")
    @PutMapping("/{id}")
    public UsuarioResponse actualizar(
            @PathVariable Long id,
            @Valid
            @RequestBody UsuarioRequest request) {

        return service.actualizar(id, request);
    }

    @Operation(summary = "Eliminar usuario")
    @DeleteMapping("/{id}")
    public void eliminar(
            @PathVariable Long id) {

        service.eliminar(id);
    }

}