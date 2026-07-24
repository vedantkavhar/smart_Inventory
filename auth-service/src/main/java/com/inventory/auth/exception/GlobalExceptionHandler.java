package com.inventory.auth.exception;
import java.util.*;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice public class GlobalExceptionHandler { @ExceptionHandler(DuplicateRecordException.class) public ResponseEntity<Map<String,String>> duplicate(DuplicateRecordException ex){Map<String,String> body=new HashMap<>();body.put("error","Duplicate Record");body.put("message",ex.getMessage());return new ResponseEntity<>(body,HttpStatus.CONFLICT);} @ExceptionHandler(MethodArgumentNotValidException.class) public ResponseEntity<Map<String,String>> validation(MethodArgumentNotValidException ex){Map<String,String> errors=new HashMap<>();ex.getBindingResult().getFieldErrors().forEach(error->errors.put(error.getField(),error.getDefaultMessage()));return ResponseEntity.badRequest().body(errors);} }
