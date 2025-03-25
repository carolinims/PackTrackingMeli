package com.meli.PackTracking.component;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.meli.PackTracking.form.HolidayForm;


@Component
public class NagerDateComponent {
	
	@Value("${nagerdate.holidays.URL}")
	private String API_URL;
	
	@Autowired
	private RestTemplate restTemplate;

    public Boolean isHoliday(LocalDate date, String countryCode) {
    	try {
	        HolidayForm[] holidaysArray = restTemplate.getForObject(API_URL, HolidayForm[].class, date.getYear(), countryCode);
	    	return Arrays.asList(holidaysArray).stream().anyMatch(holiday -> holiday.getDate().equals(date));
    	} catch (Exception e) {
            System.err.println("Erro na api NagerDate");
    		e.printStackTrace();
            return false;  // Caso a API falhe ou não retorne dados, assume que não é feriado.
        }
    }
}
