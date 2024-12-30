package com.alura.foro_hub.domain.topico;


import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;

@Table(name="topicos")
@Entity(name="Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensaje;
    private String autor;
    private String curso;

    @Column(name = "fechaCreacion")
    private LocalDateTime fechaCreacion;
    private Boolean status;
    private String respuestas;


    public Topico(@Valid RespuestaTopicoDTO respuestaTopicoDTO) {
        this.titulo = respuestaTopicoDTO.titulo();
        this.mensaje = respuestaTopicoDTO.mensaje();
        this.autor = respuestaTopicoDTO.autor();
        this.curso = respuestaTopicoDTO.curso();

        this.fechaCreacion =LocalDateTime.now();
        this.status = true;
        this.respuestas = null;

    }

    public void actualizarDatos(@Valid DatosActualizarTopico datosActualizarTopico) {

        if (datosActualizarTopico.titulo()!= null) {
            this.titulo = datosActualizarTopico.titulo();
        }
        if (datosActualizarTopico.mensaje()!= null) {
            this.mensaje = datosActualizarTopico.mensaje();
        }
        if (datosActualizarTopico.autor()!= null) {
            this.autor = datosActualizarTopico.autor();
        }
        if (datosActualizarTopico.curso()!= null) {
            this.curso = datosActualizarTopico.curso();
        }

    }




public Topico (){}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(String respuestas) {
        this.respuestas = respuestas;
    }


}
