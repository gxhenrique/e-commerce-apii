package com.projeto.ecommerceapii.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String,String>> handleValidation(ConstraintViolationException ex){
        Map<String,String> error = new HashMap<>();

        ex.getConstraintViolations().forEach(
                validation ->  error.put(validation.getPropertyPath().toString(), validation.getMessage()));

        return ResponseEntity.badRequest().body(error);

    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequest(
            BadRequestException ex) {

        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleReouseNotFound(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
