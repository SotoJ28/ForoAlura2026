package com.alura.foro.infra.security;

import jakarta.validation.constraints.NotBlank;

public record DatosLogin(
        @NotBlank String email,
        @NotBlank String contrasena
) {}
