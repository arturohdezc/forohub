package com.alura.foro_hub.domain.topico;

import jakarta.validation.constraints.NotBlank;

public record RespuestaTopicoDTO(
        @NotBlank
        String titulo,

        @NotBlank
        String mensaje,

        @NotBlank
        String autor,

        @NotBlank
        String curso


) {
}
