package com.meli.PackTracking.component;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.meli.PackTracking.form.HolidayForm;


@Component
public class NagerDateComponent {
	
	private final RestTemplate restTemplate;
    private static final String API_URL = "https://date.nager.at/ /api/v3/PublicHolidays/{Year}/{CountryCode}";
    
    public NagerDateComponent(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    
    public Boolean isHoliday(LocalDate date, String countryCode) {
    	try {
	        HolidayForm[] holidaysArray = restTemplate.getForObject(API_URL, HolidayForm[].class, date.getYear(), countryCode);
	    	return Arrays.asList(holidaysArray).stream().anyMatch(holiday -> holiday.getDate().equals(date));
    	} catch (Exception e) {
            e.printStackTrace();
            return false;  // Caso a API falhe ou não retorne dados, assume que não é feriado.
        }
    }
}
