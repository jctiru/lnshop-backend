package com.jctiru.lnshop.api.ui.model.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class OrderOverviewRest {

	private String orderId;
	private BigDecimal total;
	private LocalDateTime createDateTime;

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
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
