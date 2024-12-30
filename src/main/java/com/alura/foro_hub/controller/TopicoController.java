package com.alura.foro_hub.controller;

import com.alura.foro_hub.domain.topico.DatosListadoTopicos;
import com.alura.foro_hub.domain.topico.RespuestaTopicoDTO;
import com.alura.foro_hub.domain.topico.Topico;
import com.alura.foro_hub.domain.topico.TopicoRepository;
import com.alura.foro_hub.domain.topico.DatosActualizarTopico;
import com.alura.foro_hub.domain.topico.DatosRespuestaTopico;
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
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaTopico> crearUnTopico(@RequestBody @Valid RespuestaTopicoDTO respuestaTopicoDTO,
                                                              UriComponentsBuilder uriComponentsBuilder) {

        Topico topico = topicoRepository.save(new Topico(respuestaTopicoDTO));
        DatosRespuestaTopico datosRespuestaMedico = new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor(),
                topico.getCurso());


        URI url = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(url).body(datosRespuestaMedico);
    }

    @GetMapping
    public ResponseEntity< Page<DatosListadoTopicos>> listadoTopicos(@PageableDefault(size = 10, sort = "fechaCreacion", page = 0, direction = Sort.Direction.ASC) Pageable paginacion){

        return ResponseEntity.ok( topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopicos::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico) {


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
        Topico topico = topicoRepository.getReferenceById(id);
            topicoRepository.delete(topico);
            return ResponseEntity.noContent().build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaTopico> retornaTopico(@PathVariable Long id){

        Topico topico = topicoRepository.getReferenceById(id);
        var datosTopico =  new DatosRespuestaTopico(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getAutor(),
                topico.getCurso());

        return ResponseEntity.ok(datosTopico);
    }



}