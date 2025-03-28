package com.meli.PackTracking.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.meli.PackTracking.domain.Package;
import com.meli.PackTracking.domain.enums.PackageStatus;
import com.meli.PackTracking.repository.EventRepository;
import com.meli.PackTracking.repository.PackageRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Service
public class PackagePurgeService {
	
	private static final Logger logger = LoggerFactory.getLogger(PackagePurgeService.class);
	
	@Autowired
	private PackageRepository packageRepo;
	
	@Autowired
	private EventRepository eventRepo;
	
	@Value("${scheduler.cancelled.packages.quantity}")
	private Integer PACK_QUANTITY;

	/**
	 * Servico agendado para expugar os pacotes que foram cancelados
	 */
	@Scheduled(cron = "${scheduler.cancelled.packages.cron.expression}")
	@Transactional
	public void callDeletedCancelledPackService() {
		deleteCancelledPackages();
	}
	
	private void deleteCancelledPackages() {
		try {
			Optional<List<Package>> packs = packageRepo.findLimitedByStatus(PackageStatus.CANCELLED, PageRequest.of(0, PACK_QUANTITY));
			for(Package p : packs.get()) {
				eventRepo.deleteEventsByPackId(p.getId_pack());
				packageRepo.delete(p);
			}
			
			logger.info(String.format(
					"Scheduled purge cancelled packages service - [%s] cancelled packages were purged from the database.",
					packs.get().size()));
		} catch (Exception e) {
			logger.error(String.format("Scheduled purge cancelled packages service - ERROR \n %s.", e));
		}
	}
}
