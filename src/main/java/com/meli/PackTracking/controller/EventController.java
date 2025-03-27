package com.meli.PackTracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.PackTracking.form.EventForm;
import com.meli.PackTracking.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService eventService;

	@PostMapping("sendEvent")
	public ResponseEntity<String> sendEvent(@RequestBody EventForm eventForm) {
		eventService.saveEvent(eventForm);

		return ResponseEntity.accepted().body("Processing started");

	}
}
