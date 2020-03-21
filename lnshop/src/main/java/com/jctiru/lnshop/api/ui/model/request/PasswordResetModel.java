package com.jctiru.lnshop.api.ui.model.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class PasswordResetModel {

	@NotBlank(message = "Token must not be empty")
	private String token;

	@NotBlank(message = "Password must not be empty")
	@Size(min = 6, message = "Password must be at least 6 characters")
	private String password;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
