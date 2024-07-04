package com.proyecto.airticket.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByUsername(String username);
	
}
