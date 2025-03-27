package com.meli.PackTracking.form;

import java.util.Date;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class EventForm {
	@NotNull(message = "packageId - Required field")
	@Size(max = 255, message = "packageId must be fewer than 255 characters")
	@NotBlank(message = "packageId can not be blank")
	private String packageId;
	
	@NotNull(message = "location - Required field")
	@Size(max = 255, message = "location must be fewer than 255 characters")
	@NotBlank(message = "location can not be blank")
	private String location;
	
	@NotNull(message = "description - Required field")
	@Size(max = 255, message = "description must be fewer than 255 characters")
	@NotBlank(message = "description can not be blank")
	private String description;
	
	@NotNull(message = "date - Required field") 
	@PastOrPresent(message = "date - Should not be in the future")
	private Date date;
	
	public String generateETag() {
		return String.valueOf(Objects.hash(packageId, location, description, date));
	}
}
