package com.spring.blog.service;

import org.springframework.stereotype.Component;

import com.spring.blog.domain.User;
import com.spring.blog.dto.RegisterDTO;

@Component
public interface UserService {
	
	User register(RegisterDTO user) throws Exception;

}
