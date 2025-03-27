package com.meli.PackTracking.controller;

import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meli.PackTracking.dto.PackageDto;
import com.meli.PackTracking.form.PackageForm;
import com.meli.PackTracking.form.StatusPackForm;
import com.meli.PackTracking.service.PackageService;


@RestController
@RequestMapping("/package")
public class PackageController {

	@Autowired
	private PackageService packService;

	@PostMapping("savePack")
	public ResponseEntity<PackageDto> savePack(@Validated @RequestBody PackageForm form,
			@RequestHeader(value = HttpHeaders.IF_NONE_MATCH, required = false) String ifNoneMatch) {
		
		String eTag = String.valueOf(form.generateETag());
		if(eTag.equals(ifNoneMatch)) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
		
		PackageDto packDto = packService.savePackage(form);

		return ResponseEntity.status(HttpStatus.CREATED)
				.eTag(eTag)
				.body(packDto);

	}

	@PatchMapping("updatePackStatus/{id}")
	public ResponseEntity<PackageDto> updateStatus(@PathVariable String id, @RequestBody StatusPackForm statusForm,
			@RequestHeader(value = HttpHeaders.IF_NONE_MATCH, required = false) String ifNoneMatch) {
		
		String eTag = String.valueOf(Objects.hash(id, statusForm.getStatus()));
		if(eTag.equals(ifNoneMatch)) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
				
		PackageDto packDto = packService.updateStatusPack(id, statusForm.getStatus());

		return ResponseEntity.status(HttpStatus.OK)
				.eTag(eTag)
				.body(packDto);

	}

	@GetMapping("getPackageDetails/{id}")
	public ResponseEntity<PackageDto> getPackageDetails(@PathVariable String id,
			@RequestParam(defaultValue = "false") boolean includeEvents) {

		PackageDto packDto = packService.getPackageDetail(id, includeEvents);

		return ResponseEntity.status(HttpStatus.OK).body(packDto);

	}

	@PatchMapping("cancelPackage/{id}")
	public ResponseEntity<PackageDto> cancelPackage(@PathVariable String id,
			@RequestHeader(value = HttpHeaders.IF_NONE_MATCH, required = false) String ifNoneMatch) {

		String eTag = String.valueOf(Objects.hash(id));
		if(eTag.equals(ifNoneMatch)) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
		
		PackageDto packDto = packService.cancelPackage(id);

		return ResponseEntity.status(HttpStatus.OK)
				.eTag(eTag)
				.body(packDto);
	}
	
	@GetMapping("getListPackage")
	public ResponseEntity<List<PackageDto>> getListPackages(@RequestParam String sender, @RequestParam String recipient) {
		List<PackageDto> listPackage = packService.listPackages(sender, recipient);
		
		return ResponseEntity.status(HttpStatus.OK).body(listPackage);
	}

}
