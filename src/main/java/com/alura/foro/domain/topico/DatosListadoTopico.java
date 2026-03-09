package com.alura.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        String curso,
        String status,
        LocalDateTime fechaCreacion
) {}
