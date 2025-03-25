package com.meli.PackTracking.domain;

import java.util.Date;
import java.util.Set;

import com.meli.PackTracking.domain.enums.PackageStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Package {
	
	@Id
	private String idPack;
	private String description;
	private String funFact;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Sender sender;
	
	@OneToOne(fetch = FetchType.EAGER)
	private Recipient recipient;
	private Boolean isHolliday;
	private Date estimatedDelieveryDate;
	private Date createdAt;
	private Date updatedAt;
	
	@Enumerated(EnumType.STRING)
	private PackageStatus Status;
	
	private Date deliveredAt;
	
	@OneToMany(mappedBy = "idEvent", fetch = FetchType.LAZY)
	private Set<Event> events;	
	
}
