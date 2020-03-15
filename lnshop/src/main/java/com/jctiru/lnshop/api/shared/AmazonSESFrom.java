package com.jctiru.lnshop.api.shared;

public enum AmazonSESFrom {

	LNSHOP("lnshop@lnshop.jctiru.com", "LNShop");

	private final String email;
	private final String name;

	private AmazonSESFrom(String email, String name) {
		this.email = email;
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public String getName() {
		return name;
	}

	public String getFrom() {
		return String.format("%s <%s>", this.name, this.email);
	}

}
