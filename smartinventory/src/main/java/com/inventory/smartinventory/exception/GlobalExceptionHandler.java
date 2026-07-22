package com.inventory.smartinventory.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;



//step3 
//restcontrolleradvice = responsebody (for json ) + controlleradvice
//for gloabla exceptiong handling
//all errors of controlelers catched here 


@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleValidationException(
	        MethodArgumentNotValidException ex) {

	    Map<String, String> errors = new HashMap<>();

	    ex.getBindingResult().getFieldErrors().forEach(error -> {
	        errors.put(error.getField(), error.getDefaultMessage());
	    });

	    return errors;
	}
	
	
	
	//step 6.1 
//	we created cusotme respirc not found runime exception 
//	when exception occur from getcatbyid service method ,and not found we call resorucenot found custme exceptin 
//	resoucenotfound pass that msg to runtimeexpetion class
//	and respond 500 internal server error isnteead repsond 400 usigng @responsestatud htppstatus notfound
//	but to cusotmisze that message gloably (using the msg we passed in throw and stored in exception) we are hadnling resoucentorfoudnexcetin 
	
//	each feild of diff data type so use object for map
//	linkedhsamp for order insertingo
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, Object> handleResourceNotFoundException(ResourceNotFoundException ex) {

	    Map<String, Object> errorResponse = new LinkedHashMap<>();

	    errorResponse.put("timestamp", LocalDateTime.now());
	    errorResponse.put("status", HttpStatus.NOT_FOUND.value());
	    errorResponse.put("error", "Not Found");
	    errorResponse.put("message", ex.getMessage());

	    return errorResponse;
	}

}
