package com.spring.blog.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter{

	@Autowired
	private JwtProvider tokenProvider;
	
	@Autowired
	protected CustomUserDetailsService customUserDetailsService;
	
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// Get JWT (Token) from http Request
		
		String token = getJWTFromRequest(request);
		
		// Validate Token
		
		if(StringUtils.hasText(token) && tokenProvider.validateToken(token)) {
			// Get username from token
			String username =tokenProvider.getUsernameFromJWT(token);
			// Load user Associated with token
			UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication =new  UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());	
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
			// Set Spring security
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
		}
		
		filterChain.doFilter(request, response);
	}
	
	// Bearer <AccessToken>
	private String getJWTFromRequest(HttpServletRequest request) {
		
		
		String bearerToken = request.getHeader("Authorization");
		
		if(StringUtils.hasText("Bearer ")) {
			return bearerToken.substring(7, bearerToken.length());
		}
		
		
		
		
		return "";
		
	}
	
	

	
}