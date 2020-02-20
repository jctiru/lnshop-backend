package com.jctiru.lnshop.api.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jctiru.lnshop.api.service.LightNovelService;
import com.jctiru.lnshop.api.service.OrderService;
import com.jctiru.lnshop.api.service.StripeService;
import com.jctiru.lnshop.api.shared.dto.LightNovelDto;
import com.jctiru.lnshop.api.ui.model.request.OrderRequestModel;
import com.stripe.model.Charge;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	LightNovelService lightNovelService;

	@Autowired
	StripeService stripeService;

	@Override
	public void createOrder(OrderRequestModel orderRequest, String email) {
		Map<LightNovelDto, Integer> cartItems = new HashMap<>();
		BigDecimal totalAmount = BigDecimal.ZERO;

		for (Map.Entry<String, Integer> item : orderRequest.getCartItems().entrySet()) {
			LightNovelDto lightNovelDto = lightNovelService.getLightNovelByLightNovelId(item.getKey());
			cartItems.put(lightNovelDto, item.getValue());
			totalAmount = totalAmount.add(lightNovelDto.getPrice().multiply(new BigDecimal(item.getValue())));
		}

		Charge charge = stripeService.createCharge(orderRequest.getToken(), totalAmount, email);
		System.out.println(charge);
	}

}
