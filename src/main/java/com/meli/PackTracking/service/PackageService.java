package com.meli.PackTracking.service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.PackTracking.component.NagerDateComponent;
import com.meli.PackTracking.dto.PackageDto;
import com.meli.PackTracking.form.PackageForm;
import com.meli.PackTracking.repository.PackageRepository;
import com.meli.PackTracking.repository.SenderRepository;

@Service

public class PackageService {
	
	@Autowired
	private PackageRepository packageRepo;
	
	@Autowired
	private SenderRepository senderRepo;
	
	@Autowired
	private NagerDateComponent nagerDateComponent;
	
	public PackageDto savePackage(PackageForm form) {
		LocalDate date = form.getEstimatedDeliveryDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		
		Boolean isHoliday = nagerDateComponent.isHoliday(date, Locale.getDefault().getCountry());
		
		return null;
		
	}
}
