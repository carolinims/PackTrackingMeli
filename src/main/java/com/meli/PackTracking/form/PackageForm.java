package com.meli.PackTracking.form;

import java.util.Date;
import java.util.Objects;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class PackageForm {
	
	@NotNull(message = "description - Required field")
	@Size(max = 255, message = "description must be fewer than 255 characters")
	@NotBlank(message = "description can not be blank")
	private String description;
	
	@NotNull(message = "sender - Required field")
	@Size(max = 255, message = "sender must be fewer than 255 characters")
	@NotBlank(message = "sender can not be blank")
	private String sender;
	
	@NotNull(message = "recipient - Required field")
	@Size(max = 255, message = "recipient must be fewer than 255 characters")
	@NotBlank(message = "recipient can not be blank")
	private String recipient;
	
	@NotNull(message = "estimatedDeliveryDate - Required field") 
	@FutureOrPresent(message = "estimatedDeliveryDate - Should be in the future")
	@Getter
	private Date estimatedDeliveryDate;
	
	public String generateETag() {
        return String.valueOf(Objects.hash(description, sender, recipient, estimatedDeliveryDate));
    }
}
