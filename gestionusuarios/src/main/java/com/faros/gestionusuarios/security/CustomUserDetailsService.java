package com.faros.gestionusuarios.security;

import com.faros.gestionusuarios.entity.Usuario;
import com.faros.gestionusuarios.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository repository;

    @Override
    public UserDetails loadUserByUsername(String correo)

            throws UsernameNotFoundException {

        Usuario usuario = repository.findByCorreo(correo)

                .orElseThrow(() ->

                        new UsernameNotFoundException(

                                "Usuario no encontrado."

                        ));

        return new org.springframework.security.core.userdetails.User(

                usuario.getCorreo(),

                usuario.getPassword(),

                usuario.getEstado(),

                true,

                true,

                true,

                List.of(

                        new SimpleGrantedAuthority(

                                "ROLE_" + usuario.getRol()

                        )

                )

        );

    }

}