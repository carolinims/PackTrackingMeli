package com.meli.PackTracking.domain;

import java.util.Date;

import com.meli.PackTracking.domain.enums.PackageStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Index;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "package", indexes = {
	    @Index(name = "idx_sender", columnList = "id_sender"),
	    @Index(name = "idx_recipient", columnList = "id_recipient"),
	    @Index(name = "idx_id_pack", columnList = "id_pack")
	})
public class Package {
	
	@Id
	@Column(name = "id_pack")
	private String id_pack;
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
		
	@PrePersist
    @PreUpdate
    private void truncateFunFact() {
        if (funFact != null && funFact.length() > 255) {
            funFact = funFact.substring(0, 255); // Trunca para 255 caracteres
        }
    }
}
