package com.meli.PackTracking.controller;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.meli.PackTracking.exception.InvalidStatusPackageException;
import com.meli.PackTracking.exception.PackageNotFoundException;

@ControllerAdvice
public class ExceptionsHandlerController {
	
	@ExceptionHandler(PackageNotFoundException.class)
    public ResponseEntity<String> handlePackageNotFound(PackageNotFoundException ex) {
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
}
