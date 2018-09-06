package com.portal.web.model;

public class APIMessage {

	private String status;

	private String message;

	public APIMessage() {

	}

	public APIMessage(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "APIMessage [status=" + status + ", message=" + message + "]";
	}

}
