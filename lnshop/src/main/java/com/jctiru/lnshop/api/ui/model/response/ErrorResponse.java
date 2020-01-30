package com.jctiru.lnshop.api.ui.model.response;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;

public class ErrorResponse {

	private HttpStatus status;
	private LocalDateTime timeStamp;
	private String message;
	private List<Map<String, String>> errors;

	public ErrorResponse() {

	}

	public ErrorResponse(HttpStatus status, LocalDateTime timeStamp, String message, List<Map<String, String>> errors) {
		this.setStatus(status);
		this.timeStamp = timeStamp;
		this.message = message;
		this.errors = errors;
	}

	public ErrorResponse(HttpStatus status, LocalDateTime timeStamp, String message, Map<String, String> error) {
		this.setStatus(status);
		this.timeStamp = timeStamp;
		this.message = message;
		this.errors = Arrays.asList(error);
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public LocalDateTime getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(LocalDateTime timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Map<String, String>> getErrors() {
		return errors;
	}

	public void setErrors(List<Map<String, String>> details) {
		this.errors = details;
	}

}
