package com.meli.PackTracking.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.PackTracking.domain.Sender;

public interface SenderRepository extends JpaRepository<Sender, Long>{

}
