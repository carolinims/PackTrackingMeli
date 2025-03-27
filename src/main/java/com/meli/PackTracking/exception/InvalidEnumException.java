package com.meli.PackTracking.exception;

public class InvalidEnumException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public InvalidEnumException(String message) {
        super(message);
    }
}
