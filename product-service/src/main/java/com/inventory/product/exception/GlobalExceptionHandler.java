package com.inventory.product.exception;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice public class GlobalExceptionHandler {
 @ExceptionHandler(ResourceNotFoundException.class) public ResponseEntity<Map<String,String>> notFound(ResourceNotFoundException ex) { Map<String,String> response=new HashMap<>(); response.put("error","Resource Not Found"); response.put("message",ex.getMessage()); return new ResponseEntity<>(response,HttpStatus.NOT_FOUND); }
 @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<Map<String,String>> validation(MethodArgumentNotValidException ex) { Map<String,String> errors=new HashMap<>(); ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage())); return ResponseEntity.badRequest().body(errors); }
}
