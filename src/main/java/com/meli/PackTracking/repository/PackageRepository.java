package com.meli.PackTracking.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.meli.PackTracking.domain.Package;
import com.meli.PackTracking.domain.Recipient;
import com.meli.PackTracking.domain.Sender;
import com.meli.PackTracking.domain.enums.PackageStatus;

public interface PackageRepository  extends JpaRepository<Package, String> {

	@Query("SELECT p FROM Package p WHERE p.sender = :sender")
	Optional<Set<Package>> getBySender(@Param("sender") Sender sender); 
	
	@Query("SELECT p FROM Package p WHERE p.recipient = :recipient")
	Optional<Set<Package>> getByRecipient(@Param("recipient") Recipient recipient); 
	
	@Query("SELECT p FROM Package p WHERE p.Status = :status")
	Optional<List<Package>> findLimitedByStatus(@Param("status") PackageStatus status, Pageable pageable);
		
}
