package com.jctiru.lnshop.api.ui.model.response;

import java.math.BigDecimal;

public class OrderItemRest {

	private LightNovelRest lightNovel;
	private int quantity;
	private BigDecimal price;
	private BigDecimal subtotal;

	public LightNovelRest getLightNovel() {
		return lightNovel;
	}

	public void setLightNovel(LightNovelRest lightNovel) {
		this.lightNovel = lightNovel;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public BigDecimal getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}

}
