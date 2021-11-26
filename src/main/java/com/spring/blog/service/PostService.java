package com.spring.blog.service;

import org.springframework.stereotype.Component;

import com.spring.blog.dto.PostDTO;
import com.spring.blog.response.PostResponse;

@Component
public interface PostService {

	PostDTO createPost(PostDTO postDto);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);

    PostDTO getPostById(long id);

    PostDTO updatePost(PostDTO postDto, long id);

    void deletePostById(long id);
    
    
    
}
