package com.jctiru.lnshop.api.shared.dto;

import java.math.BigDecimal;

public class OrderItemDto {

	private long id;
	private LightNovelDto lightNovel;
	private int quantity;
	private BigDecimal price;
	private BigDecimal subtotal;
	private OrderDto order;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public LightNovelDto getLightNovel() {
		return lightNovel;
	}

	public void setLightNovel(LightNovelDto lightNovel) {
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

	public OrderDto getOrder() {
		return order;
	}

	public void setOrder(OrderDto order) {
		this.order = order;
	}

}
