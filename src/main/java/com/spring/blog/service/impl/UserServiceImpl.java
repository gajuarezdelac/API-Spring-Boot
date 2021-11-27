package com.spring.blog.service.impl;
import java.util.Collections;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.spring.blog.domain.Role;
import com.spring.blog.domain.User;
import com.spring.blog.dto.RegisterDTO;
import com.spring.blog.repository.RoleRepository;
import com.spring.blog.repository.UserRepository;
import com.spring.blog.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	private BCryptPasswordEncoder passwordEncoder;
	
	public UserServiceImpl (UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}


	@Override
	public User register(RegisterDTO userDTO) throws Exception {
		
		validateUsername(userDTO.getUsername());
		User user =  new User();
		user.setNames(userDTO.getNames());
		user.setSurnames(userDTO.getSurnames());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setUsername(userDTO.getUsername());
		Role role = roleRepository.findRoleByName("ROLE_USER");
		user.setRoles(Collections.singleton(role));
		userRepository.save(user);
	
		return user;
	}
	
	
	private User validateUsername(String username) throws Exception {
		
		User user = userRepository.findUserByUsername(username);
		
		if(user != null) {
		 throw new Exception("Usernarme already exist");
		}
		
		return user;
	}
		
}
