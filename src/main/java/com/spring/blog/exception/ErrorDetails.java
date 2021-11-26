package com.spring.blog.exception;

import java.util.Date;
import java.util.List;

public class ErrorDetails {
	
	private Date timeStamp;
	
	private String message;
	
	private String details;
	
	private List<String> listErrors;

	/**
	 * @param timeStamp
	 * @param message
	 * @param details
	 */
	public ErrorDetails(Date timeStamp, String message, String details, List<String> list) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
		this.listErrors = list;
	}
	
	/**
	 * @param timeStamp
	 * @param message
	 * @param details
	 */
	public ErrorDetails(Date timeStamp, String message, String details) {
		super();
		this.timeStamp = timeStamp;
		this.message = message;
		this.details = details;
	}

	/**
	 * @return the timeStamp
	 */
	public Date getTimeStamp() {
		return timeStamp;
	}

	/**
	 * @param timeStamp the timeStamp to set
	 */
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
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
	 * @return the details
	 */
	public String getDetails() {
		return details;
	}

	/**
	 * @param details the details to set
	 */
	public void setDetails(String details) {
		this.details = details;
	}

	/**
	 * @return the listErrors
	 */
	public List<String> getListErrors() {
		return listErrors;
	}

	/**
	 * @param listErrors the listErrors to set
	 */
	public void setListErrors(List<String> listErrors) {
		this.listErrors = listErrors;
	}
	
	
	
	
	
	
	
}
