package com.projeto.ecommerceapii.exception;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class HandleNotFound {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseError> handleNotFound(EntityNotFoundException e, HttpServletRequest request){

        ResponseError error = new ResponseError();

        error.setTimestamp(Instant.now());
        error.setError("Resouse not found");
        error.setMessage(e.getMessage());
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setPath(request.getRequestURI());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
}
