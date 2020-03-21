package com.jctiru.lnshop.api.ui.model.request;

import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class OrderRequestModel {

	@NotBlank(message = "Stripe token id must not be empty")
	private String stripeTokenId;

	@NotEmpty(message = "Cart must not be empty")
	private Map<@NotBlank(message = "Novel id must not be empty") String, @Min(value = 1, message = "Cart item quantity minimum of 1") Integer> cartItems;

	@NotNull(message = "Address args must not be null")
	private Object addressArgs;

	public String getStripeTokenId() {
		return stripeTokenId;
	}

	public void setStripeTokenId(String stripeTokenId) {
		this.stripeTokenId = stripeTokenId;
	}

	public Map<String, Integer> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, Integer> cartItems) {
		this.cartItems = cartItems;
	}

	public Object getAddressArgs() {
		return addressArgs;
	}

	public void setAddressArgs(Object addressArgs) {
		this.addressArgs = addressArgs;
	}

}
