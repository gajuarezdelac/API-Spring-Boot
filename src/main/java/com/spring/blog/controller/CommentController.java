/**
 * 
 */
package com.spring.blog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.blog.dto.CommentDTO;
import com.spring.blog.service.CommentService;

/**
 * @author gabriel.juarez
 *
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	private CommentService commentService;
	
	CommentController(CommentService commentService) {
		this.commentService = commentService;
	}

	@PostMapping("/{postId}")
	public ResponseEntity<CommentDTO> createComment(@PathVariable(value = "postId") long postId, @RequestBody CommentDTO commentDTO) {
		return new ResponseEntity<>(commentService.createComment(postId, commentDTO), HttpStatus.CREATED);
	}
	
	@GetMapping("/{postId}") 
	public ResponseEntity<List<CommentDTO>> getCommentsByPostID(@PathVariable(value = "postId") long postId) {
		return new ResponseEntity<>(commentService.getCommentsByPostId(postId), HttpStatus.CREATED);
	}

	@GetMapping( "/{postId}/{commentId}" ) 
	public ResponseEntity<CommentDTO> getCommentsByID(@PathVariable(value = "postId") long postId, @PathVariable(value = "commentId") long commentId) {
		CommentDTO response =  commentService.findCommentById(postId, commentId);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
	
	@PutMapping("/{postId}/{commentId}")
	public ResponseEntity<CommentDTO> updateComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "commentId") Long commentId,@RequestBody CommentDTO commentRequest) {
		CommentDTO response = commentService.updateComment(postId, commentId, commentRequest);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

	
	@DeleteMapping("/{postId}/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId, @PathVariable(value = "postId") Long commentId) {
		
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<>("Comment deleted successfully",HttpStatus.OK);
	}
	
	
}
