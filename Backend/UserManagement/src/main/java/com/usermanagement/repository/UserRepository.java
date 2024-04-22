package com.usermanagement.repository;

import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.usermanagement.model.User;


@Transactional
public interface UserRepository extends JpaRepository<User,Integer>{
	
	@Query("select u from User u where u.username = :username")
	Optional<User> findByUserName(@Param("username") String username);
	
}
