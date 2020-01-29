package com.jctiru.lnshop.api.security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(401);
		response.setContentType("application/json");
		response.getWriter().append(jsonResponse());
	}

	private String jsonResponse() {
		BadCredentialsResponse response = new BadCredentialsResponse();
		response.setTimestamp(LocalDateTime.now());
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setError("Unauthorized");
		response.setMessage("Authentication failed: bad credentials");
		response.setPath(SecurityConstants.SIGN_IN_URL);

		String jsonResponse = "";

		try {
			jsonResponse = objectMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return jsonResponse;
	}

	private class BadCredentialsResponse {
		private LocalDateTime timestamp;
		private int status;
		private String error;
		private String message;
		private String path;

		public LocalDateTime getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(LocalDateTime timestamp) {
			this.timestamp = timestamp;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}

	}
}
