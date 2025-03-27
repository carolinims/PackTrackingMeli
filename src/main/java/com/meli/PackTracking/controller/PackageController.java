package com.meli.PackTracking.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meli.PackTracking.domain.enums.PackageStatus;
import com.meli.PackTracking.dto.PackageDto;
import com.meli.PackTracking.form.PackageForm;
import com.meli.PackTracking.form.StatusPackForm;
import com.meli.PackTracking.service.PackageService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/package")
public class PackageController {

	@Autowired
	private PackageService packService;

	@PostMapping("savePack")
	public ResponseEntity<PackageDto> savePack(@RequestBody @Validated PackageForm form) {
		PackageDto packDto = packService.savePackage(form);

		return ResponseEntity.status(HttpStatus.CREATED).body(packDto);

	}

	@PatchMapping("updatePackStatus/{id}")
	public ResponseEntity<PackageDto> updateStatus(@PathVariable String id, @RequestBody StatusPackForm statusForm) {
		PackageDto packDto = packService.updateStatusPack(id, statusForm.getStatus());

		return ResponseEntity.status(HttpStatus.OK).body(packDto);

	}

	@GetMapping("getPackageDetails/{id}")
	public ResponseEntity<PackageDto> getPackageDetails(@PathVariable String id,
			@RequestParam(defaultValue = "false") boolean includeEvents) {

		PackageDto packDto = packService.getPackageDetail(id, includeEvents);

		return ResponseEntity.status(HttpStatus.OK).body(packDto);

	}

	@PatchMapping("cancelPackage/{id}")
	public ResponseEntity<PackageDto> cancelPackage(@PathVariable String id) {

		PackageDto packDto = packService.cancelPackage(id);

		return ResponseEntity.status(HttpStatus.OK).body(packDto);
	}
	
	@GetMapping("getListPackage")
	public ResponseEntity<List<PackageDto>> getListPackages(@RequestParam String sender, @RequestParam String recipient) {
		List<PackageDto> listPackage = packService.listPackages(sender, recipient);
		
		return ResponseEntity.status(HttpStatus.OK).body(listPackage);
	}

}
