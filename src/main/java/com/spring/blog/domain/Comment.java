/**
 * 
 */
package com.spring.blog.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author gabriel.juarez
 *
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {


    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
	private long id;
    
    @Column(nullable = false)
	private String name;
	
    @Column(nullable = false)
	private String email;
	
    @Column(nullable = false)
	private String body;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;
	
}
