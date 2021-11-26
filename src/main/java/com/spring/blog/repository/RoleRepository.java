/**
 * 
 */
package com.spring.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.blog.domain.Role;

/**
 * @author gabriel.juarez
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long>{

	Role findRoleByName(String name);
	
}
