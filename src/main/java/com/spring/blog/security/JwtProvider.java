package com.spring.blog.security;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.spring.blog.exception.BlogAPIException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

// Esta clase se encarga de generar el token 
@Component
public class JwtProvider {

	@Value("app.jwt-secret")
	private String jwtSecret;
	
	@Value("app.jwt-expiration-milliseconds")
	private String jwtExpiration;
	
	public String generateToken(Authentication authentication)  {
		
		String username = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime());
		
		String token = Jwts.builder()
				.setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	
		return token;
	}
	
	// Get Username from token
	public String getUsernameFromJWT(String token) {
		Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
	
	
	public boolean validateToken(String token) {
		
		List<String> list = new ArrayList();
		
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Signature",list);
		} catch (MalformedJwtException e) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Invalid JWT Token",list);
		} catch (ExpiredJwtException e) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Expired JWT Token",list);
		} catch (UnsupportedJwtException e) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Unsupported JWT Token",list);
		} catch (IllegalArgumentException e) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "JWT Claims string is empty",list);
		}
	
	}
	
	
	
}
