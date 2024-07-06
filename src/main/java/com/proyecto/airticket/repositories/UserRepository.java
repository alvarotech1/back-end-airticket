package com.proyecto.airticket.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.airticket.user.Users;



public interface UserRepository extends JpaRepository<Users, Integer> {

	Optional<Users> findByUsername(String username);
	


}
