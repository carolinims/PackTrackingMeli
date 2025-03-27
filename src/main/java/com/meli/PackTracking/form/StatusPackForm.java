package com.meli.PackTracking.form;

import com.meli.PackTracking.domain.enums.PackageStatus;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class StatusPackForm {

	private PackageStatus status;
}
