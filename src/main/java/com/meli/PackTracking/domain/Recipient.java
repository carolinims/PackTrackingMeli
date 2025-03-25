package com.meli.PackTracking.domain;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
public class Recipient {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idRecipient;
	private String name;
	
	@OneToMany(mappedBy = "idPack", fetch = FetchType.LAZY)
	private Set<Package> packages;
	
}
