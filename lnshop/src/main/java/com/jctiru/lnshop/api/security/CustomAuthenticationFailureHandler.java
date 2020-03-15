package com.jctiru.lnshop.api.security;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
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
		int httpCode;

		if (exception.getClass() == BadCredentialsException.class) {
			httpCode = 401;
		} else {
			// DisabledException
			httpCode = 403;
		}

		response.setStatus(httpCode);
		response.setContentType("application/json");
		response.getWriter().append(jsonResponse(httpCode));
	}

	private String jsonResponse(int httpCode) {
		BadCredentialsResponse response = new BadCredentialsResponse();
		response.setTimestamp(LocalDateTime.now());

		if (httpCode == 401) {
			response.setStatus(HttpStatus.UNAUTHORIZED.value());
			response.setError("Unauthorized");
			response.setMessage("Authentication failed: bad credentials");
		} else {
			// 403
			response.setStatus(HttpStatus.FORBIDDEN.value());
			response.setError("Forbidden");
			response.setMessage("Authentication failed: user disabled");
		}

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
