package com.jctiru.lnshop.api.ui.model.response;

import java.util.List;

public class OrderOverviewPageRest {

	private int totalPages;
	private List<OrderOverviewRest> orders;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<OrderOverviewRest> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderOverviewRest> orders) {
		this.orders = orders;
	}

}
