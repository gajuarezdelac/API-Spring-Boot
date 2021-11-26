package com.spring.blog.dto;

import java.util.Set;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class PostDTO {

    private long id;
    
    @NotEmpty( message = "El campo titulo no puede venir vacio")
    @Size( min = 2 , message = "Post title should have at least  2 characters")
    private String title;
    
    @NotEmpty( message = "the description cannot be null")
    private String description;
    
    private String content;
    
    private Set<CommentDTO> comments;
    
	
}
