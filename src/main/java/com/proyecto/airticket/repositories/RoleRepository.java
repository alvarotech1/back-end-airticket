package com.proyecto.airticket.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proyecto.airticket.role.Role;
import com.proyecto.airticket.role.RoleEnum;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByName(RoleEnum name);
}
