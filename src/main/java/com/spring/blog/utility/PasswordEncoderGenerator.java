/**
 * 
 */
package com.spring.blog.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author gabriel.juarez
 *
 */
public class PasswordEncoderGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PasswordEncoder passwordEncoder =  new BCryptPasswordEncoder();	
		System.out.println(passwordEncoder.encode("password"));
	}

}
