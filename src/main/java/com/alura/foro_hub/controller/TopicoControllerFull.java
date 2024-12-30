/*package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.topico.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
@SecurityRequirement(name = "bearer-key")
public class TopicoControllerFull {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearUnTopico(@RequestBody @Valid RespuestaTopicoDTO respuestaTopicoDTO,
                                                              UriComponentsBuilder uriComponentsBuilder) {

        Optional<Topico> existingTitulo = topicoRepository.findByTitulo(respuestaTopicoDTO.titulo());

        if (existingTitulo.isPresent()) {
            DatosRespuestaTopico datosError = new DatosRespuestaTopico(
                    null,
                    "Este titulo ya esta dado de alta",
                    null,
                    null,
                    null
            );
            return ResponseEntity.badRequest().body(datosError);
        }

        Optional<Topico> existingMensaje = topicoRepository.findByMensaje(respuestaTopicoDTO.mensaje());
        if (existingMensaje.isPresent()) {
            DatosRespuestaTopico datosError = new DatosRespuestaTopico(
                    null,
                    null,
                    "Este mensaje ya está dado de alta" ,
                    null,
                    null
            );
            return ResponseEntity.badRequest().body(datosError);
        }


        Topico topico = topicoRepository.save(new Topico(respuestaTopicoDTO));
        DatosRespuestaTopico datosRespuestaMedico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor(),
                topico.getCurso());

        // Crear URL para el nuevo recurso
        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity< Page<DatosListadoTopicos>> listadoTopicos(@PageableDefault(size = 10, sort = "fechaCreacion", page = 0, direction = Sort.Direction.ASC) Pageable paginacion){

        return ResponseEntity.ok( topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopicos::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {

        Optional<Topico> existingTitulo = topicoRepository.findByTitulo(datosActualizarTopico.titulo());
        if (existingTitulo.isPresent() && !existingTitulo.get().getId().equals(datosActualizarTopico.id())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El título ya está registrado.");
        }


        Optional<Topico> existingMensaje = topicoRepository.findByMensaje(datosActualizarTopico.mensaje());
        if (existingMensaje.isPresent() && !existingMensaje.get().getId().equals(datosActualizarTopico.id())) {

            return ResponseEntity.status(HttpStatus.CONFLICT).body("El mensaje ya está registrado.");
        }


        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());


        topico.actualizarDatos(datosActualizarTopico);


        return ResponseEntity.ok(new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor(),
                topico.getCurso()));
    }


    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);

        if (optionalTopico.isPresent()) {
            topicoRepository.delete(optionalTopico.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaTopico(@PathVariable Long id){
        Optional<Topico> topicoOptional = topicoRepository.findById(id);

        if (topicoOptional.isEmpty()) {

            return ResponseEntity.notFound().build();
        }

        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico =  new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor(),
                topico.getCurso());

        return ResponseEntity.ok(datosTopico);
    }



}*/