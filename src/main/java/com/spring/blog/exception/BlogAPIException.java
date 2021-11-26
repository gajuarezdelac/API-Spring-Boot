package com.spring.blog.exception;

import java.util.List;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	private HttpStatus status;
	private String message;
	private List<String> list;
	
	
	public BlogAPIException(HttpStatus status, String message, List<String> list) {
		this.status = status;
		this.message = message;
		this.list = list;
	}
	
	public BlogAPIException(String message, HttpStatus status, String message1) {
		super(message);
		this.status = status;
		this.message = message1;
	}

	public HttpStatus getStatus() {
		return status;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the list
	 */
	public List<String> getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(List<String> list) {
		this.list = list;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	
	
	
	
	
	
	
}
