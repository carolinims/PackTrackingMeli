package com.meli.PackTracking.form;

import java.util.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PackageForm {
	
	@NotBlank(message = "description - Required field")
	private String description;
	
	@NotBlank(message = "sender - Required field")
	private String sender;
	
	@NotBlank(message = "recipient - Required field")
	private String recipient;
	
	@NotNull(message = "estimatedDeliveryDate - Required field") 
	@Future(message = "estimatedDeliveryDate - Should be in the future")
	@Getter
	private Date estimatedDeliveryDate;
}
