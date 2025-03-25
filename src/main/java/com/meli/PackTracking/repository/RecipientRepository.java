package com.meli.PackTracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.PackTracking.domain.Recipient;

public interface RecipientRepository extends JpaRepository<Recipient, Long>{

}
