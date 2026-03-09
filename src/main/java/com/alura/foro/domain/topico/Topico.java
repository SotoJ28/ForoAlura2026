package com.alura.foro.domain.topico;

import com.alura.foro.domain.usuario.Usuario;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion = LocalDateTime.now();

    private String status = "ABIERTO";

    private String curso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "autor_id")
    private Usuario autor;

    private Boolean activo = true;

    public Topico() {}

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getMensaje() { return mensaje; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public String getStatus() { return status; }
    public String getCurso() { return curso; }
    public Usuario getAutor() { return autor; }
    public Boolean getActivo() { return activo; }

    public Topico(String titulo, String mensaje, String curso, Usuario autor) {
        this.titulo = titulo;
        this.mensaje = mensaje;
        this.curso = curso;
        this.autor = autor;
    }

    public void eliminar() {
        this.activo = false;
    }

    public void actualizar(DatosActualizarTopico datos) {
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.curso = datos.curso();
    }

}
