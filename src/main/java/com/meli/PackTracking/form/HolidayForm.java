package com.meli.PackTracking.form;

import java.time.LocalDate;

import lombok.Getter;

@Getter
public class HolidayForm {
	private LocalDate date;
    private String name;
    private String localName;
    private String countryCode;
      
}
