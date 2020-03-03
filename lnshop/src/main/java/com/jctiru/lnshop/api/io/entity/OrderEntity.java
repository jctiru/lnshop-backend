package com.jctiru.lnshop.api.io.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "`order`")
public class OrderEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "order_id", nullable = false)
	private String orderId;

	@Column(name = "stripe_token_id", nullable = false)
	private String stripeTokenId;

	@Column(name = "stripe_charge_id", nullable = false)
	private String stripeChargeId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	@Embedded
	@AttributeOverride(name = "brand", column = @Column(name = "card_brand", nullable = false))
	@AttributeOverride(name = "funding", column = @Column(name = "card_funding", nullable = false))
	@AttributeOverride(name = "last4", column = @Column(name = "card_last4", nullable = false))
	private Card card;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, optional = false)
	private ShippingAddressEntity shippingAddress;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItemEntity> orderItems;

	@Column(name = "total", nullable = false)
	private BigDecimal total;

	@CreationTimestamp
	@Column(name = "create_date_time", nullable = false)
	private LocalDateTime createDateTime;

	@UpdateTimestamp
	@Column(name = "update_date_time", nullable = false)
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

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
	}

	public ShippingAddressEntity getShippingAddress() {
		return shippingAddress;
	}

	// Modified for syncing one-to-one bidirectional association
	public void setShippingAddress(ShippingAddressEntity shippingAddress) {
		if (shippingAddress == null) {
			if (this.shippingAddress != null) {
				this.shippingAddress.setOrder(null);
			}
		} else {
			shippingAddress.setOrder(this);
		}
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

	// Helper method
	public void addOrderItem(OrderItemEntity orderItem) {
		this.orderItems.add(orderItem);
		orderItem.setOrder(this);
	}

}
