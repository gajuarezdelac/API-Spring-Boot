package com.spring.blog.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.spring.blog.domain.Comment;
import com.spring.blog.domain.Post;
import com.spring.blog.dto.CommentDTO;
import com.spring.blog.exception.BlogAPIException;
import com.spring.blog.exception.ResourceNotFoundException;
import com.spring.blog.repository.CommentRepository;
import com.spring.blog.repository.PostRepository;
import com.spring.blog.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	private CommentRepository commentRepository;
	private PostRepository postRepository;
	private ModelMapper mapper;

	private CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
		this.commentRepository = commentRepository;
		this.postRepository = postRepository; 
		this.mapper = mapper;
	}

	@Override
	public CommentDTO createComment(long postId, CommentDTO commentDTO) {
		
		Comment comment = mapToEntity(commentDTO);
		// Retrieve post entity by ID
		Post post  = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("POST", "id", postId));
		// Set Post to comment entity
		comment.setPost(post);
		// Get return value save
		Comment newComment =  commentRepository.save(comment);
		
		return mapToDTO(newComment);
	}

	@Override
	public List<CommentDTO> getCommentsByPostId(long postId) {
		// Retrieve comments by postId
		List<Comment> comments = commentRepository.findByPostId(postId);
		// Convert to list of comments entities to list of comment dto´s
		return comments.stream().map(comment -> mapToDTO(comment)).collect(Collectors.toList());
	}
	
	@Override
	public CommentDTO findCommentById(long postId, long commentId) {
		// TODO Auto-generated method stub
		
		// Retrieve post entity by ID
		Post post  = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("POST", "id", postId));
		Comment comment =  commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("COMMENT", "id", postId));
		
		List<String> listError = new ArrayList<>();
		listError.add("Error Blog Resource");
		listError.add("Error Blog Resource 2");
		listError.add("Error Blog Resource 3");
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comments does not belong to post", listError);
		}
		
		return mapToDTO(comment);
	}
	
	@Override
	public CommentDTO updateComment(long postId, long commentId, CommentDTO commentRequest) {
		
		Post post  = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("POST", "id", postId));
		Comment comment =  commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("COMMENT", "id", postId));
		
		List<String> listError = new ArrayList<>();
		listError.add("update Resource");
		listError.add("update Resource 2");
		listError.add("Update Resource 3");
		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comments does not belong to post", listError);
		}
		
		comment.setEmail(commentRequest.getEmail());
		comment.setBody(commentRequest.getBody());
		comment.setName(commentRequest.getName());
		Comment updateComment = commentRepository.save(comment);
		
		return mapToDTO(updateComment);
	}

	private CommentDTO mapToDTO(Comment comment) {

		CommentDTO commentDTO = mapper.map(comment, CommentDTO.class);
		
//		commentDTO.setId(comment.getId());
//		commentDTO.setName(comment.getName());
//		commentDTO.setEmail(comment.getEmail());
//		commentDTO.setBody(comment.getBody());
		
		return commentDTO;
		
	}
	
	private Comment mapToEntity(CommentDTO commentDTO) {
		
		Comment comment = mapper.map(commentDTO, Comment.class);
		
//		comment.setId(commentDTO.getId());
//		comment.setName(commentDTO.getName());
//		comment.setEmail(commentDTO.getEmail());
//		comment.setBody(commentDTO.getBody());
		
		return comment;
	}

	@Override
	public void deleteComment(long postId, long commentId) {
		
		Post post  = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("POST", "id", postId));
		Comment comment =  commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("COMMENT", "id", commentId));
	
		List<String> listError = new ArrayList<>();
		listError.add("Error delete Resource");
		listError.add("Error delete Resource 2");
		listError.add("Error delete Resource 3");

		
		if(!comment.getPost().getId().equals(post.getId())) {
			throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comments does not belong to post", listError);
		}
				
		commentRepository.delete(comment);
	}
	
}
