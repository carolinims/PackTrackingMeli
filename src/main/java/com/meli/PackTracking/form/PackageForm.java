package com.meli.PackTracking.form;

import java.util.Date;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class PackageForm {
	
	@NotBlank(message = "description - Campo obrigatorio")
	private String description;
	
	@NotBlank(message = "sender - Campo obrigatorio")
	private String sender;
	
	@NotBlank(message = "recipient - Campo obrigatorio")
	private String recipient;
	
	@NotNull(message = "estimatedDeliveryDate - Campo obrigatorio") 
	@Future(message = "estimatedDeliveryDate precisa ser no futuro")
	@Getter
	private Date estimatedDeliveryDate;
}
