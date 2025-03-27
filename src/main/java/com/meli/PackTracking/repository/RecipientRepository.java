package com.meli.PackTracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.meli.PackTracking.domain.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, Long>{
	
	@Query("SELECT r FROM Recipient r WHERE r.name = :name")
	Recipient findByName(@Param("name") String name);
}
