package com.meli.PackTracking.exception;

public class PackageNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PackageNotFoundException(String message) {
        super(message);
    }

}
