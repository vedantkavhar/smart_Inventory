package com.inventory.supplier.exception;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) public ResponseEntity<Map<String, String>> notFound(ResourceNotFoundException ex) { Map<String, String> body = new HashMap<>(); body.put("error", "Resource Not Found"); body.put("message", ex.getMessage()); return new ResponseEntity<>(body, HttpStatus.NOT_FOUND); }
    @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<Map<String, String>> validation(MethodArgumentNotValidException ex) { Map<String, String> errors = new HashMap<>(); ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage())); return ResponseEntity.badRequest().body(errors); }
}
