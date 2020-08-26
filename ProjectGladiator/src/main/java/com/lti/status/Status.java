package com.lti.status;

public class Status {
	
	private int userId;
	private StatusType status;
	private String message;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public StatusType getStatus() {
		return status;
	}

	public void setStatus(StatusType status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public static enum StatusType {
		SUCCESS, FAILURE;
	}

}
