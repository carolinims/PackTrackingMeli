package com.meli.PackTracking.controller;

import java.sql.SQLException;
import java.util.stream.Collectors;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.meli.PackTracking.exception.InvalidEnumException;
import com.meli.PackTracking.exception.InvalidStatusPackageException;
import com.meli.PackTracking.exception.IDNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class ExceptionsHandlerController {

	private static final Logger logger = LoggerFactory.getLogger(ExceptionsHandlerController.class);

	@ExceptionHandler(IDNotFoundException.class)
	public ResponseEntity<String> handlePackageNotFound(IDNotFoundException ex) {
		logger.error(ex.getStackTrace().toString());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(InvalidStatusPackageException.class)
	public ResponseEntity<String> handleInvalidStatusPackage(InvalidStatusPackageException ex) {
		logger.error(ex.getStackTrace().toString());
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationExceptions(MethodArgumentNotValidException ex) {

		String messages = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.joining("\n"));
		logger.error(messages);

		return new ResponseEntity<>(messages, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidEnumException.class)
	public ResponseEntity<String> handleInvalidEnum(InvalidEnumException ex) {
		logger.error("Stack trace: ", ex);
		return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<String> handleMissingRequestHeader(MissingRequestHeaderException ex) {
		String errorMessage = "Missing required header: " + ex.getMessage();
		logger.error(ex.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGenericException(Exception ex) {
		logger.error("Stack trace: ", ex);
		return new ResponseEntity<>("An internal error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<String> handleRuntimeException(RuntimeException ex) {
		logger.error("Stack trace: ", ex);
	    return new ResponseEntity<>("Erro interno no servidor: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(NullPointerException.class)
	public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
		logger.error("Stack trace: ", ex);
		return new ResponseEntity<>("Null Pointer Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
		logger.error("Stack trace: ", ex);
        return new ResponseEntity<>("DataBase access Error" , HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
		logger.error("Stack trace: ", ex);
        return new ResponseEntity<>("DataBase Error ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException ex) {
		logger.error("Stack trace: ", ex);
        return new ResponseEntity<>("DataBase Error ", HttpStatus.INTERNAL_SERVER_ERROR);
    }
	
	@ExceptionHandler(SQLException.class)
	public ResponseEntity<String> handleSQLException(SQLException ex) {
		logger.error("Stack trace: ", ex);
	    return new ResponseEntity<>("DataBase Error ", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
