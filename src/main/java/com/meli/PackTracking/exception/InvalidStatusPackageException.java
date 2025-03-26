package com.meli.PackTracking.exception;

public class InvalidStatusPackageException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public InvalidStatusPackageException(String message) {
        super(message);
    }
}
