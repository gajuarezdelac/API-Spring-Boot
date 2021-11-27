package com.spring.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.domain.User;
import com.spring.blog.dto.LoginDTO;
import com.spring.blog.dto.RegisterDTO;
import com.spring.blog.service.UserService;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private UserService userService;
	
	@PostMapping("/signin")
	public ResponseEntity<String> createComment(@RequestBody LoginDTO loginDTO) {
		
		Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return new ResponseEntity<>("User signed-in successfully ", HttpStatus.CREATED);
	}
	
	@PostMapping("/register") 
	public ResponseEntity<User> registerUser(@RequestBody RegisterDTO params) throws Exception {
		User user = userService.register(params);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	

}
