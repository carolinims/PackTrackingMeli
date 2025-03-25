package com.meli.PackTracking;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meli.PackTracking.component.NagerDateComponent;

@SpringBootApplication
public class PackTrackingApplication {
	
	@Autowired
	private final NagerDateComponent nagerDateComponent;

	public static void main(String[] args) {
		SpringApplication.run(PackTrackingApplication.class, args);
	}
	
	public PackTrackingApplication(NagerDateComponent nagerDateComponent) {
		this.nagerDateComponent = nagerDateComponent;
	}

	 @RestController
	    class HelloWorldController {

	        @GetMapping("/")
	        public String hello() {

	    		Boolean isHoliday = nagerDateComponent.isHoliday(LocalDate.of(2025, 4, 18), Locale.getDefault().getCountry());
	            return "Hello, World!, Finalmenteeeeeee" + isHoliday;
	        }
	    }
	 
}
