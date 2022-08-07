package com.deepak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.deepak.entities.Patient;

public interface PatientRepository extends JpaRepository<Patient,Integer> {
	
	boolean existsByEmail(String email);
}
