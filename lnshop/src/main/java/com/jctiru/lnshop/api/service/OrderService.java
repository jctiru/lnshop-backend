package com.jctiru.lnshop.api.service;

import org.springframework.security.core.Authentication;

import com.jctiru.lnshop.api.shared.dto.OrderDto;
import com.jctiru.lnshop.api.shared.dto.OrderPageDto;
import com.jctiru.lnshop.api.ui.model.request.OrderRequestModel;

public interface OrderService {

	void createOrder(OrderRequestModel orderRequest, String email);

	OrderPageDto getOrders(int page, int limit);

	OrderPageDto getOrders(int page, int limit, String email);

	OrderDto getOrderByOrderId(String orderId, Authentication authentication);

}
