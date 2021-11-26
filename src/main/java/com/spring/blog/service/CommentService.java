package com.spring.blog.service;

import java.util.List;

import org.springframework.stereotype.Component;
import com.spring.blog.dto.CommentDTO;

@Component
public interface CommentService {

	CommentDTO createComment(long postId, CommentDTO commentDTO);

	List<CommentDTO> getCommentsByPostId(long postId);
	
	CommentDTO findCommentById(long postId, long commentId);
	
	CommentDTO updateComment(long postId, long commentId, CommentDTO commentRequest);
	
	void deleteComment(long postId, long commentId);
	
}
