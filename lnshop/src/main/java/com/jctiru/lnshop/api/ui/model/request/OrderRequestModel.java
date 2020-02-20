package com.jctiru.lnshop.api.ui.model.request;

import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class OrderRequestModel {

	@NotEmpty(message = "Token must not be empty")
	private String token;

	@NotEmpty(message = "Cart must not be empty")
	private Map<@NotBlank(message = "Novel id must not be empty") String, @Min(value = 1, message = "Cart item quantity minimum of 1") Integer> cartItems;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Map<String, Integer> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, Integer> cartItems) {
		this.cartItems = cartItems;
	}

}
