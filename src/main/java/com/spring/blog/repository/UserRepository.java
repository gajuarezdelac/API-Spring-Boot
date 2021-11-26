package com.spring.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findUserByUsername(String username);
	
	Boolean existsUserByUsername(String username);
	
	
}
