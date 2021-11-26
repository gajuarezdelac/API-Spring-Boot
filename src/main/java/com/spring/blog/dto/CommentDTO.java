/**
 * 
 */
package com.spring.blog.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author gabriel.juarez
 *
 */
@Data
public class CommentDTO {

	private long id;
	
	@NotEmpty( message = "the name cannot be null")
	private String name;
	
	@NotEmpty( message = "the email cannot be null")
	@Email
	private String email;
		
	private String body;
	    
}
