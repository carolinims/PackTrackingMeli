package com.meli.PackTracking.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.meli.PackTracking.domain.Event;
import com.meli.PackTracking.domain.Package;

public interface EventRepository extends JpaRepository<Event, Long>{
	
	@Query("SELECT e FROM Event e WHERE e.pack.id_pack = :id")
	Optional<List<Event>> findByPack(@Param("id") String packId);
	
	@Modifying
	@Query("DELETE FROM Event e WHERE e.pack.id_pack =:id")
	void deleteEventsByPackId(String id);
}
