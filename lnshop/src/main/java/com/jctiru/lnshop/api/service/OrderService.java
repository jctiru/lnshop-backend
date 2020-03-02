package com.jctiru.lnshop.api.service;

import com.jctiru.lnshop.api.shared.dto.OrderDto;
import com.jctiru.lnshop.api.shared.dto.OrderPageDto;
import com.jctiru.lnshop.api.ui.model.request.OrderRequestModel;

public interface OrderService {

	void createOrder(OrderRequestModel orderRequest, String email);

	OrderDto getOrderByOrderId(String orderId);

	OrderPageDto getOrders(int page, int limit);

}
