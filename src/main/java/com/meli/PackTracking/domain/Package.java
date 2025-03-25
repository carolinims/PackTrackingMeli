package com.meli.PackTracking.domain;

import java.util.Date;
import java.util.Set;

import com.meli.PackTracking.domain.enums.PackageStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
	
	@Column(length = 255)
	private String funFact;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idSender")
	private Sender sender;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idRecipient")
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
	
	@PrePersist
    @PreUpdate
    private void truncateFunFact() {
        if (funFact != null && funFact.length() > 255) {
            funFact = funFact.substring(0, 255); // Trunca para 255 caracteres
        }
    }
}
