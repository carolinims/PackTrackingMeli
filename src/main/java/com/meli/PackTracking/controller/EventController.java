package com.meli.PackTracking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
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
	public ResponseEntity<String> sendEvent(@Validated @RequestBody EventForm eventForm,
			@RequestHeader(value = HttpHeaders.IF_NONE_MATCH, required = true) String ifNoneMatch) {
		
		String eTag = String.valueOf(eventForm.generateETag());
		if(eTag.equals(ifNoneMatch)) {
			return ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
		}
		
		eventService.saveEvent(eventForm);

		return ResponseEntity.accepted()
				.eTag(eTag)
				.body("Processing started");

	}
}
