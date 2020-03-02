package com.jctiru.lnshop.api.shared.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.jctiru.lnshop.api.io.entity.OrderItemEntity;
import com.jctiru.lnshop.api.io.entity.ShippingAddressEntity;
import com.jctiru.lnshop.api.io.entity.UserEntity;

public class OrderDto {

	private long id;
	private String orderId;
	private String stripeTokenId;
	private String stripeChargeId;
	private UserEntity user;
	private ShippingAddressEntity shippingAddress;
	private List<OrderItemEntity> orderItems;
	private BigDecimal total;

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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public ShippingAddressEntity getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(ShippingAddressEntity shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public List<OrderItemEntity> getOrderItems() {
		return orderItems;
	}

	public void setOrderItems(List<OrderItemEntity> orderItems) {
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

	private LocalDateTime createDateTime;
	private LocalDateTime updateDateTime;

}
