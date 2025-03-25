package com.meli.PackTracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.PackTracking.domain.Event;

public interface EventRepository extends JpaRepository<Event, Long>{

}
