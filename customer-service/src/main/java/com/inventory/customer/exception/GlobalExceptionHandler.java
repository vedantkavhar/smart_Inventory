package com.inventory.customer.exception;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> notFound(ResourceNotFoundException ex) { return response("Resource Not Found", ex.getMessage(), HttpStatus.NOT_FOUND); }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validation(MethodArgumentNotValidException ex) { Map<String, String> errors = new HashMap<>(); ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage())); return ResponseEntity.badRequest().body(errors); }
    private ResponseEntity<Map<String, String>> response(String error, String message, HttpStatus status) { Map<String, String> body = new HashMap<>(); body.put("error", error); body.put("message", message); return new ResponseEntity<>(body, status); }
}
