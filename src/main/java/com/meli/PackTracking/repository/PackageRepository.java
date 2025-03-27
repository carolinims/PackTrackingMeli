package com.meli.PackTracking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.PackTracking.domain.Package;

public interface PackageRepository  extends JpaRepository<Package, String> { 
	
}
