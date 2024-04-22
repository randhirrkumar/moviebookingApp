package com.usermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.usermanagement.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer>{
	
}

