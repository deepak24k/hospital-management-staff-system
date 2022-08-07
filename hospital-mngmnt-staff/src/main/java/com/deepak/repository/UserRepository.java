package com.deepak.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.deepak.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	boolean existsByEmail(String email);
	
	User findByEmail(String email);
}
