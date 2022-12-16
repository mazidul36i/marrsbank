package com.masai.app.exceptions;

import java.time.LocalDateTime;

public class MyErrorDetails {
	
	
	private LocalDateTime timestamp;
	private String messege;
	private String details;
	
	
	
	public MyErrorDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MyErrorDetails(LocalDateTime timestamp, String messege, String details) {
		super();
		this.timestamp = timestamp;
		this.messege = messege;
		this.details = details;
	}
	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessege() {
		return messege;
	}
	public void setMessege(String messege) {
		this.messege = messege;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	
	

}
