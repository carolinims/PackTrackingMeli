package com.meli.PackTracking.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.meli.PackTracking.exception.InvalidEnumException;
import com.meli.PackTracking.exception.InvalidStatusPackageException;
import com.meli.PackTracking.exception.ResourceNotFoundException;

@ControllerAdvice
public class ExceptionsHandlerController {
	
	@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handlePackageNotFound(ResourceNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

	@ExceptionHandler(InvalidStatusPackageException.class)
    public ResponseEntity<String> handleInvalidStatusPackage(InvalidStatusPackageException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

		String messages = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.joining("\n"));
		
        return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(InvalidEnumException.class)
    public ResponseEntity<String> handleInvalidEnum(InvalidEnumException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	 @ExceptionHandler(MissingRequestHeaderException.class)
	    @ResponseStatus(HttpStatus.BAD_REQUEST)  
	    public ResponseEntity<String> handleMissingRequestHeader(MissingRequestHeaderException ex) {
	        String errorMessage = "Missing required header: " + ex.getMessage();
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	    }
}
