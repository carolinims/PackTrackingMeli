package com.meli.PackTracking.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

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
	private List<EventDto> events;

	public static PackageDto convertFromDomain(Package pack) {
		PackageDto dto = new PackageDto();
				dto.setId(pack.getId_pack());
				dto.setDescription(pack.getDescription()); 
				dto.setSender(pack.getSender().getName()); 
				dto.setRecipient(pack.getRecipient().getName()); 
				dto.setStatus(pack.getStatus()); 
				dto.setCreatedAt(pack.getCreatedAt()); 
				dto.setUpdatedAt(pack.getUpdatedAt()); 
				dto.setDeliveredAt(pack.getDeliveredAt());
				
		 return dto;
	}
	
	public static PackageDto convertFromDomainWithEvents(Package pack, List<Event> events) {
		PackageDto dto = convertFromDomain(pack);
		
		dto.setEvents(new ArrayList<EventDto>());
		if(events != null) {
			dto.events.addAll(events.stream()
                    .filter(Objects::nonNull) // Filtra eventos nulos
                    .map(EventDto::convertEventFromDomain)
                    .toList());
		}
		
		return dto;
	}
	
	
}
