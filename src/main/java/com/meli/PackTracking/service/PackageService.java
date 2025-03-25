package com.meli.PackTracking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meli.PackTracking.component.DogAPIComponent;
import com.meli.PackTracking.component.NagerDateComponent;
import com.meli.PackTracking.domain.Package;
import com.meli.PackTracking.domain.Recipient;
import com.meli.PackTracking.domain.Sender;
import com.meli.PackTracking.domain.enums.PackageStatus;
import com.meli.PackTracking.dto.PackageDto;
import com.meli.PackTracking.form.PackageForm;
import com.meli.PackTracking.repository.PackageRepository;
import com.meli.PackTracking.repository.RecipientRepository;
import com.meli.PackTracking.repository.SenderRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Service
public class PackageService {
	
	@Autowired
	private PackageRepository packageRepo;
	
	@Autowired
	private SenderRepository senderRepo;
	
	@Autowired
	private RecipientRepository recipientRepo;
	
	@Autowired
	private NagerDateComponent nagerDateComponent;
	
	@Autowired
	private DogAPIComponent dogapiComponent;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	
	public PackageDto savePackage(PackageForm form) {

		
		Package pack = new Package();
		pack.setIdPack(createPackId());
		pack.setDescription(form.getDescription());
		pack.setFunFact(getDogFunFact());
		pack.setSender(getOrCreateSender(form.getSender()));
		pack.setRecipient(getOrCreateRecipient(form.getRecipient()));
		pack.setIsHolliday(isHoliday(form.getEstimatedDeliveryDate()));
		pack.setEstimatedDelieveryDate(form.getEstimatedDeliveryDate());
		pack.setCreatedAt(new Date());
		pack.setUpdatedAt(new Date());
		pack.setStatus(PackageStatus.CREATED);

		packageRepo.save(pack);

		return PackageDto.convertFromDomain(pack);
	}
	
	/**
	 * Buscar um sender pelo name/descricao enviado no endpoint, se encontrar utiliza oq ja existe,
	 * se nao encontrar cria um sender novo
	 * @param name
	 * @return Sender
	 */
	private Sender getOrCreateSender(String name) {		
		Sender s = senderRepo.findByName(name);	
		return s != null ? s : senderRepo.save(new Sender(name));
	}
	
	/**
	 * Buscar um recipient pelo name/descricao enviado no endpoint, se encontrar utiliza oq ja existe,
	 * se nao encontrar cria um recipient novo
	 * @param name
	 * @return Recipient
	 */
	private Recipient getOrCreateRecipient(String name) {		
		Recipient r = recipientRepo.findByName(name);	
		return r != null ? r : recipientRepo.save(new Recipient(name));
	}
	
	private String createPackId() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
        return "pack-" + LocalDateTime.now().format(formatter);
        		
	}
	
	private Boolean isHoliday(Date date) {
		LocalDate dateHoliday = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return nagerDateComponent.isHoliday(dateHoliday, Locale.getDefault().getCountry());
	}
	
	private String getDogFunFact() {
		return dogapiComponent.getDogFunFact();
	}
}
