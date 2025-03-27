package com.meli.PackTracking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.meli.PackTracking.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long>{
	
	@Query("SELECT e FROM Event e WHERE e.pack.id_pack = :id")
	Optional<List<Event>> findByPack(@Param("id") String packId);
}
