package com.meli.PackTracking.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meli.PackTracking.component.DogAPIComponent;
import com.meli.PackTracking.component.NagerDateComponent;
import com.meli.PackTracking.domain.Event;
import com.meli.PackTracking.domain.Package;
import com.meli.PackTracking.domain.Recipient;
import com.meli.PackTracking.domain.Sender;
import com.meli.PackTracking.domain.enums.PackageStatus;
import com.meli.PackTracking.dto.PackageDto;
import com.meli.PackTracking.exception.InvalidStatusPackageException;
import com.meli.PackTracking.exception.PackageNotFoundException;
import com.meli.PackTracking.form.PackageForm;
import com.meli.PackTracking.repository.EventRepository;
import com.meli.PackTracking.repository.PackageRepository;
import com.meli.PackTracking.repository.RecipientRepository;
import com.meli.PackTracking.repository.SenderRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;


@Service
public class PackageService {

	@Autowired
	private PackageRepository packageRepo;

	@Autowired
	private SenderRepository senderRepo;

	@Autowired
	private RecipientRepository recipientRepo;
	
	@Autowired
	private EventRepository eventRepo;

	@Autowired
	private NagerDateComponent nagerDateComponent;

	@Autowired
	private DogAPIComponent dogapiComponent;

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public PackageDto savePackage(PackageForm form) {

		Package pack = new Package();
		pack.setId_pack(createPackId());
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
	 * Buscar um sender pelo name/descricao enviado no endpoint, se encontrar
	 * utiliza oq ja existe, se nao encontrar cria um sender novo
	 * 
	 * @param name
	 * @return Sender
	 */
	private Sender getOrCreateSender(String name) {
		Sender s = senderRepo.findByName(name);
		return s != null ? s : senderRepo.save(new Sender(name));
	}

	/**
	 * Buscar um recipient pelo name/descricao enviado no endpoint, se encontrar
	 * utiliza oq ja existe, se nao encontrar cria um recipient novo
	 * 
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

	@Transactional
	public PackageDto updateStatusPack(String packId, PackageStatus status) {
		Optional<Package> pack = Optional.of(packageRepo.findById(packId)
				.orElseThrow(() -> new PackageNotFoundException(String.format("Package id [%s] Not found!", packId))));

		switch (pack.get().getStatus()) {
		case CREATED:
			if (status.equals(PackageStatus.IN_TRANSIT)) {
				pack.get().setStatus(status);
				pack.get().setUpdatedAt(new Date());
				packageRepo.save(pack.get());
			} else {
				throw new InvalidStatusPackageException(
						String.format("Invalid status [%s] for package [%s]", status, packId));
			}
			break;

		case IN_TRANSIT:
			if (status.equals(PackageStatus.DELIVERED)) {
				pack.get().setStatus(status);
				pack.get().setUpdatedAt(new Date());
				pack.get().setDeliveredAt(new Date());
				packageRepo.save(pack.get());
			} else {
				throw new InvalidStatusPackageException(
						String.format("Invalid status [%s] for package [%s]", status, packId));
			}
			break;

		case CANCELLED:
			throw new InvalidStatusPackageException(
					String.format("Invalid status [%s] for package [%s] - package is cancelled", status, packId));

		case DELIVERED:// quando ja foi entregue nao faz sentido mandar mais status
			throw new InvalidStatusPackageException(
					String.format("Invalid status [%s] for package [%s] - package already delivered", status, packId));
		}

		return PackageDto.convertFromDomain(pack.get());
	}

	@Transactional
	public PackageDto getPackageDetail(String packId, Boolean isIncludeDetails) {
		Optional<Package> pack = null;
		pack = Optional.of(packageRepo.findById(packId)
				.orElseThrow(() -> new PackageNotFoundException(String.format("Package id [%s] Not found!", packId))));
		
		Optional<List<Event>> events = null;
		if (isIncludeDetails) {
			events = eventRepo.findByPack(pack.get().getId_pack());
			return PackageDto.convertFromDomainWithEvents(pack.get(), events.get());
		} 

		return PackageDto.convertFromDomain(pack.get());
	}

	@Transactional
	public PackageDto cancelPackage(String packId) {
		Optional<Package> pack = Optional.of(packageRepo.findById(packId)
				.orElseThrow(() -> new PackageNotFoundException(String.format("Package id [%s] Not found!", packId))));

		if (pack.get().getStatus().equals(PackageStatus.CANCELLED)) {
			throw new InvalidStatusPackageException(
					String.format("Package [%s] can not be canceled because it has already been cancelled.", packId));
		} else if (!pack.get().getStatus().equals(PackageStatus.DELIVERED)) {
			pack.get().setStatus(PackageStatus.CANCELLED);
			pack.get().setUpdatedAt(new Date());
			packageRepo.save(pack.get());
		} else {
			throw new InvalidStatusPackageException(
					String.format("Package [%s] can not be canceled because it has already been delivered.", packId));
		}

		PackageDto packDto = new PackageDto();
		packDto.setId(pack.get().getId_pack());
		packDto.setStatus(pack.get().getStatus());
		packDto.setUpdatedAt(pack.get().getUpdatedAt());

		return packDto;
	}
}
