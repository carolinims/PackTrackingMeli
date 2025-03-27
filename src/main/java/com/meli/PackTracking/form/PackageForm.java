package com.meli.PackTracking.form;

import java.util.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PackageForm {
	
	@NotNull(message = "description - Required field")
	private String description;
	
	@NotNull(message = "sender - Required field")
	private String sender;
	
	@NotNull(message = "recipient - Required field")
	private String recipient;
	
	@NotNull(message = "estimatedDeliveryDate - Required field") 
	@Future(message = "estimatedDeliveryDate - Should be in the future")
	@Getter
	private Date estimatedDeliveryDate;
}
