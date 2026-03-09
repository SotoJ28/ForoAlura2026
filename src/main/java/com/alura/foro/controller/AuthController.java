package com.alura.foro.controller;

import com.alura.foro.infra.security.*;
import jakarta.validation.Valid;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping("/auth")
    public DatosTokenJWT login(@RequestBody @Valid DatosLogin datos) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(datos.email(), datos.contrasena())
        );
        return new DatosTokenJWT(tokenService.generarToken(auth.getName()));
    }
}

