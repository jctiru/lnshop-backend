package com.jctiru.lnshop.api.shared.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.jctiru.lnshop.api.io.entity.Card;

public class OrderDto {

	private long id;
	private String orderId;
	private String stripeTokenId;
	private String stripeChargeId;
	private UserDto user;
	private Card card;
	private ShippingAddressDto shippingAddress;
	private List<OrderItemDto> orderItems;
	private BigDecimal total;
	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getStripeTokenId() {
		return stripeTokenId;
	}

	public void setStripeTokenId(String stripeTokenId) {
		this.stripeTokenId = stripeTokenId;
	}

	public String getStripeChargeId() {
		return stripeChargeId;
	}

	public void setStripeChargeId(String stripeChargeId) {
		this.stripeChargeId = stripeChargeId;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public ShippingAddressDto getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddressDto shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderItemDto> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemDto> orderItems) {
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

	public LocalDateTime getUpdateDateTime() {
		return updateDateTime;
	}

	public void setUpdateDateTime(LocalDateTime updateDateTime) {
		this.updateDateTime = updateDateTime;
	}

}
