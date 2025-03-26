package com.meli.PackTracking.form;

import com.meli.PackTracking.domain.enums.PackageStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class StatusPackForm {

	@NotBlank(message = "status - IN_TRANSIT|CREATED|DELIVERED is Required")
	private PackageStatus status;
}
