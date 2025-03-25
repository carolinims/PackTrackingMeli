package com.meli.PackTracking.domain.enums;

public enum PackageStatus {
	IN_TRANSIT("IN_TRANSIT"),
	CREATED("CREATED"),
	DELIVERED("CREATED");
	
	private String cod;
	
	private PackageStatus(String cod) {
		this.cod = cod;
	}
	
}
