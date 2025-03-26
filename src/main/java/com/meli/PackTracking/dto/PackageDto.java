package com.meli.PackTracking.dto;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.meli.PackTracking.domain.Event;
import com.meli.PackTracking.domain.Package;
import com.meli.PackTracking.domain.enums.PackageStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class PackageDto {
	private String id;
	private String description;
	private String sender;
	private String recipient;
	private PackageStatus status;
	private Date createdAt;
	private Date updatedAt;
	private Date deliveredAt;
	private Set<Event> events;

	public static PackageDto convertFromDomain(Package pack) {
		return new PackageDto(pack.getIdPack(), pack.getDescription(), pack.getSender().getName(),
				pack.getRecipient().getName(), pack.getStatus(), pack.getCreatedAt(), pack.getUpdatedAt(),
				pack.getDeliveredAt(), pack.getEvents());
	}
}
