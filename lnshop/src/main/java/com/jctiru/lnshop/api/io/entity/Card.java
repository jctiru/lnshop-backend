package com.jctiru.lnshop.api.io.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Card {

	private String brand;
	private String funding;
	private String last4;

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getFunding() {
		return funding;
	}

	public void setFunding(String funding) {
		this.funding = funding;
	}

	public String getLast4() {
		return last4;
	}

	public void setLast4(String last4) {
		this.last4 = last4;
	}

}
