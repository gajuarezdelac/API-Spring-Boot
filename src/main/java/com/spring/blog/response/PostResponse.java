package com.spring.blog.response;

import java.util.List;

import com.spring.blog.dto.PostDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostResponse {
	
	 private List<PostDTO> content;
	 private int pageNo;
	 private int pageSize;
	 private long totalElements;
	 private int totalPages;
	 private boolean last;

}
