package com.meli.PackTracking.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum PackageStatus {
	IN_TRANSIT("IN_TRANSIT"), CREATED("CREATED"), DELIVERED("DELIVERED"), CANCELLED("CANCELLED");

	private final String value;

	PackageStatus(String value) {
		this.value = value;
	}

	@JsonValue
	public String getValue() {
		return value;
	}

	@JsonCreator
	public static PackageStatus fromString(String value) {
		for (PackageStatus status : PackageStatus.values()) {
			if (status.value.equalsIgnoreCase(value)) {
				return status;
			}
		}
		throw new IllegalArgumentException("Unknown status: " + value);
	}

}
