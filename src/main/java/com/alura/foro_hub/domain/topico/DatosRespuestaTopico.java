package com.alura.foro_hub.domain.topico;

public record DatosRespuestaTopico(
        Long id,
        String titulo,

        String mensaje,

        String autor,

        String curso) {
}
