/**
 * 
 */
package com.spring.blog.response;

import lombok.Data;

/**
 * @author gabriel.juarez
 *
 */

@Data
public class JwtAuthResponse {

	private String accessToken;
	
	private String tokenType = "Bearer";	
	
	 public JwtAuthResponse(String accessToken) {
	        this.accessToken = accessToken;
	 }
	 
}
