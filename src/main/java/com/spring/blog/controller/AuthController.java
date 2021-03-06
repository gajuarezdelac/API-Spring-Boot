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
import com.spring.blog.response.JwtAuthResponse;
import com.spring.blog.security.JwtProvider;
import com.spring.blog.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@Api(value = "Auth Controller V1")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;	
	
	@Autowired 
	private UserService userService;
	
	@ApiOperation(value = "Endpoint for signin")
	@PostMapping("/signin")
	public ResponseEntity<JwtAuthResponse> createComment(@RequestBody LoginDTO loginDTO) {
		
		Authentication authentication =  authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		// Get token
		String token = jwtProvider.generateToken(authentication);
	
		return new ResponseEntity<>(new JwtAuthResponse(token), HttpStatus.CREATED);
	}
	
	@ApiOperation(value = "Endpoint for register user")
	@PostMapping("/register") 
	public ResponseEntity<User> registerUser(@RequestBody RegisterDTO params) throws Exception {
		User user = userService.register(params);
		return new ResponseEntity<>(user, HttpStatus.CREATED);
	}
	
	

}
