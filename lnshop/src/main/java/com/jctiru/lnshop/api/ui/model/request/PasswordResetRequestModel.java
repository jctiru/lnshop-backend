package com.jctiru.lnshop.api.ui.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class PasswordResetRequestModel {

	@NotBlank(message = "Email must not be empty")
	@Email(message = "Email must be a valid email address")
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
