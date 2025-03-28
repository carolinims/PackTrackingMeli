package com.meli.PackTracking.exception;

public class IDNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IDNotFoundException(String message) {
        super(message);
    }

}
