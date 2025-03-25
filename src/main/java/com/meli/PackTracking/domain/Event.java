package com.meli.PackTracking.domain;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Event {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvent;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Package pack;
	private String location;
	private String description;
	private Date dateHour;
}
