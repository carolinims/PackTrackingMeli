package com.meli.PackTracking.dto;

import java.util.Date;

import com.meli.PackTracking.domain.Package;
import com.meli.PackTracking.domain.enums.PackageStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PackageDto {
	private String id;
	private String description;
	private String sender;
	private String recipient;
	private PackageStatus status;
	private Date createdAt;
	private Date updatedAt;
	
	public static PackageDto convertFromDomain(Package pack) {
		return new PackageDto(pack.getIdPack(), pack.getDescription(), pack.getSender().getName(), pack.getRecipient().getName(), 
				pack.getStatus(), pack.getCreatedAt(), pack.getUpdatedAt());
	}
}
