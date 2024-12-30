package com.alura.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository <Topico, Long> {
    Page<Topico> findByStatusTrue(Pageable paginacion);

    Optional<Topico> findByTitulo(@NotBlank String titulo);

    Optional<Topico> findByMensaje(@NotBlank String mensaje);
}
