/**
 * 
 */
package com.spring.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.blog.domain.Post;

/**
 * @author gabriel.juarez
 *
 */
@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	
}
