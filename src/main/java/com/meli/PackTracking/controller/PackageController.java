package com.meli.PackTracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.PackTracking.dto.PackageDto;
import com.meli.PackTracking.form.PackageForm;
import com.meli.PackTracking.service.PackageService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/package")
public class PackageController {
	
	@Autowired
	private PackageService packService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<PackageDto> savePack(@RequestBody PackageForm form) {
		PackageDto packDto = packService.savePackage(form);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(packDto) ;
		
	}

}
