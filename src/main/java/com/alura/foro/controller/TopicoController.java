package com.alura.foro.controller;

import com.alura.foro.domain.topico.*;
import com.alura.foro.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    private final TopicoRepository topicoRepository;
    private final UsuarioRepository usuarioRepository;

    public TopicoController(TopicoRepository topicoRepository, UsuarioRepository usuarioRepository) {
        this.topicoRepository = topicoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping
    public Page<DatosListadoTopico> listar(
            @PageableDefault(size = 10, sort = "fechaCreacion", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        return topicoRepository.findByActivoTrue(pageable)
                .map(t -> new DatosListadoTopico(
                        t.getId(),
                        t.getTitulo(),
                        t.getMensaje(),
                        t.getCurso(),
                        t.getStatus(),
                        t.getFechaCreacion()
                ));
    }

    @GetMapping("/{id}")
    public DatosDetalleTopico detallar(@PathVariable Long id) {

        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no existe"));

        if (!Boolean.TRUE.equals(topico.getActivo())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no existe");
        }

        return new DatosDetalleTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso(),
                topico.getStatus(),
                topico.getFechaCreacion()
        );
    }

    @PostMapping
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> registrar(@RequestBody @Valid DatosRegistroTopico datos) {

        var autor = usuarioRepository.findById(datos.autorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor no existe"));

        var topico = new Topico(datos.titulo(), datos.mensaje(), datos.curso(), autor);
        topicoRepository.save(topico);

        var respuesta = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso(),
                topico.getStatus(),
                topico.getFechaCreacion()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {

        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no existe"));

        if (!Boolean.TRUE.equals(topico.getActivo())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico ya fue eliminado");
        }

        topico.eliminar();

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DatosRespuestaTopico> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizarTopico datos
    ) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no existe"));

        if (!Boolean.TRUE.equals(topico.getActivo())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tópico no existe");
        }

        topico.actualizar(datos);

        var respuesta = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getCurso(),
                topico.getStatus(),
                topico.getFechaCreacion()
        );

        return ResponseEntity.ok(respuesta);
    }

}
