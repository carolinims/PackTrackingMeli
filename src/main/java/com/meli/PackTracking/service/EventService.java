package com.meli.PackTracking.service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.meli.PackTracking.domain.Event;
import com.meli.PackTracking.domain.Package;
import com.meli.PackTracking.exception.ResourceNotFoundException;
import com.meli.PackTracking.form.EventForm;
import com.meli.PackTracking.repository.EventRepository;
import com.meli.PackTracking.repository.PackageRepository;


@Service
@EnableAsync
public class EventService {
	
	@Autowired
	private EventRepository eventRepo;
	
	@Autowired
	private PackageRepository packageRepo;

	@Async
	@Transactional
	public CompletableFuture<Void> saveEvent(EventForm eventForm) {
		try {
			Optional<Package> pack = Optional
					.of(packageRepo.findById(eventForm.getPackageId()).orElseThrow(() -> new ResourceNotFoundException(
							String.format("Package id [%s] Not found!", eventForm.getPackageId()))));

			Event event = new Event(pack.get(), eventForm.getLocation(), eventForm.getDescription(), eventForm.getDate());
			eventRepo.save(event);
			System.out.println(String.format("Created event [%s] for package [%s]", event.getIdEvent(), event.getPack().getId_pack()));

		} catch (ResourceNotFoundException e) {
			System.err.println(String.format("Error process saveEvent async [%s] ", e.getMessage()));
		}

		return CompletableFuture.completedFuture(null);
	}
}
