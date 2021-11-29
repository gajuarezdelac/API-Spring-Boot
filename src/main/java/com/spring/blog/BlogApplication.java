package com.spring.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.spring.blog.domain.Role;
import com.spring.blog.repository.RoleRepository;

@SpringBootApplication
public class BlogApplication implements CommandLineRunner{

	@Autowired
	private RoleRepository roleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		roleRepository.save(adminRole);
		
		Role userRole = new Role();
		userRole.setName("ROLE_ADMIN");
		roleRepository.save(userRole);
	
	}
	
	

}
