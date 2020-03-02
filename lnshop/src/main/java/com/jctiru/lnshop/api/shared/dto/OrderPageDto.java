package com.jctiru.lnshop.api.shared.dto;

import java.util.List;

public class OrderPageDto {

	private int totalPages;
	private List<OrderDto> orders;

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<OrderDto> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderDto> orders) {
		this.orders = orders;
	}

}
