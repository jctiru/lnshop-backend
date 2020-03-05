package com.jctiru.lnshop.api.ui.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.jctiru.lnshop.api.io.entity.Card;

public class OrderDetailsRest {

	private String orderId;
	private UserRest user;
	private Card card;
	private ShippingAddressRest shippingAddress;
	private List<OrderItemRest> orderItems;
	private BigDecimal total;
	private LocalDateTime createDateTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public UserRest getUser() {
		return user;
	}

	public void setUser(UserRest user) {
		this.user = user;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public ShippingAddressRest getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddressRest shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderItemRest> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemRest> orderItems) {
		this.orderItems = orderItems;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public LocalDateTime getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(LocalDateTime createDateTime) {
		this.createDateTime = createDateTime;
	}

}
