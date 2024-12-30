package com.alura.foro_hub.infra.errores;

import com.alura.foro_hub.domain.ValidacionException;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErrores {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarError404(){
        return  ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){

        var errores = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList();

        return  ResponseEntity.badRequest().body(errores);
    }

    @ExceptionHandler(ValidacionException.class)
    public ResponseEntity tratarErrorDeValidacion(ValidacionException e){

        return  ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<String> handleDataIntegrityViolation(DataIntegrityViolationException e) {
        String extractedMessage = e.getMessage()
                .lines()
                .filter(line -> line.contains("Duplicate entry"))
                .map(line -> line.substring(0, line.indexOf("]", line.indexOf("Duplicate entry")) + 1))
                .findFirst()
                .orElse("Error de integridad de datos: mensaje no procesable.");

        return ResponseEntity.badRequest().body(extractedMessage);
    }





    private record DatosErrorValidacion (String campo, String error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }

}
