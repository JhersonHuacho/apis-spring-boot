package dev.huachin.java.spring.api_concert_tickets.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // @ControllerAdvice se usa para manejar excepciones globalmente en la aplicación.
    // Aquí puedes definir métodos para manejar excepciones específicas o generales.
    // Por ejemplo, puedes manejar excepciones de tipo EntityNotFoundException o ValidationException.
    // Puedes usar anotaciones como @ExceptionHandler para definir métodos que manejen excepciones específicas.
    // @ResponseStatus se usa para definir el código de estado HTTP que se devolverá en la respuesta.
    // @ExceptionHandler se usa para definir el método que manejará la excepción específica.

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException exception) {
        // Aquí puedes manejar las excepciones globales de tu aplicación
        // Por ejemplo, puedes capturar excepciones de validación y devolver un mensaje personalizado

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        Map<String, String> errors = new HashMap<>();

        for (FieldError fieldError : fieldErrors) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        var response = new HashMap<String, Object>();
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("message", "Se produjeron errores de validación");
        response.put("errors", errors);

        return ResponseEntity.badRequest().body(response);
    }
}
