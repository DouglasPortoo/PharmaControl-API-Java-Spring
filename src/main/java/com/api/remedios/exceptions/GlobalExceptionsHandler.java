package com.api.remedios.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionsHandler {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handler404() {

    return ResponseEntity.notFound().build();

  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<?> handler400(MethodArgumentNotValidException ex) {
    var errors = ex.getFieldErrors();

    return ResponseEntity.badRequest().body(errors.stream().map(DataError::new).toList());

  }

  public record DataError(String field, String message) {
    public DataError(FieldError erro){
      this(erro.getField(), erro.getDefaultMessage());
    }
  }

}
