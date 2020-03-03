package com.jctiru.lnshop.api.ui.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.jctiru.lnshop.api.io.entity.Card;

public class OrderOverviewRest {

	private String orderId;
	private Card card;
	private BigDecimal total;
	private LocalDateTime createDateTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Card getCard() {
		return card;
	}

	public void setCard(Card card) {
		this.card = card;
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
