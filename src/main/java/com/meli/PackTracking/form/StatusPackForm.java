package com.meli.PackTracking.form;

import com.meli.PackTracking.domain.enums.PackageStatus;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class StatusPackForm {

	@NotNull(message = "status - Required field")
	@NotBlank(message = "status can not be blank")
	private PackageStatus status;
}
