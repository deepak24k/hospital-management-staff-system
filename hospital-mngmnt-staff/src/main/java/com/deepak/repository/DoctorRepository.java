package com.deepak.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deepak.entities.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
	

}
