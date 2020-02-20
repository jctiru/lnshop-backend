package com.jctiru.lnshop.api.service;

import com.jctiru.lnshop.api.ui.model.request.OrderRequestModel;

public interface OrderService {

	public void createOrder(OrderRequestModel orderRequest, String email);

}
