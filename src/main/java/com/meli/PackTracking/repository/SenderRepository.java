package com.meli.PackTracking.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.meli.PackTracking.domain.Sender;

public interface SenderRepository extends JpaRepository<Sender, Long>{

	@Query("SELECT s FROM Sender s WHERE s.name = :name")
	Sender findByName(@Param("name") String name);
}
