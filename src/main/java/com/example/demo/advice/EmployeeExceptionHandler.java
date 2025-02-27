package com.example.demo.advice;

import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class EmployeeExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, Object>> handleJsonParseError(HttpMessageNotReadableException ex) {
        Map<String, Object> response = new HashMap<>();
        response.put("timestamp", LocalDateTime.now());
        response.put("status", HttpStatus.BAD_REQUEST.value());
        response.put("error", "Error en el formato del JSON");

        String message = "Verifica los datos enviados, hay un problema con los tipos de valores.";
        String fieldName = null;

        // Verificar si la causa es un MismatchedInputException (error de deserialización)
        if (ex.getCause() instanceof MismatchedInputException mismatchedEx) {
            if (!mismatchedEx.getPath().isEmpty()) {
                fieldName = mismatchedEx.getPath().get(0).getFieldName(); // Obtener el nombre del campo con error
            }
            message = "El campo '" + fieldName + "' tiene un formato incorrecto.";
        }

        response.put("message", message);
        response.put("field", fieldName);
        response.put("path", "/save"); // Puedes obtener la ruta dinámicamente si es necesario
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidArguments(MethodArgumentNotValidException e) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("timestamp", LocalDateTime.now());
        errors.put("status", HttpStatus.BAD_REQUEST.value());
        e.getBindingResult().getFieldErrors().forEach(
                fieldError -> {
                    errors.put(fieldError.getField(), fieldError.getDefaultMessage());
                }
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

}
