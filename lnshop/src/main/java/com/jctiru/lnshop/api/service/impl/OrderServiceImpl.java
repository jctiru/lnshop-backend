package com.jctiru.lnshop.api.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jctiru.lnshop.api.io.entity.LightNovelEntity;
import com.jctiru.lnshop.api.io.entity.OrderEntity;
import com.jctiru.lnshop.api.io.entity.OrderItemEntity;
import com.jctiru.lnshop.api.io.entity.ShippingAddressEntity;
import com.jctiru.lnshop.api.io.entity.UserEntity;
import com.jctiru.lnshop.api.io.repository.LightNovelRepository;
import com.jctiru.lnshop.api.io.repository.OrderRepository;
import com.jctiru.lnshop.api.io.repository.UserRepository;
import com.jctiru.lnshop.api.service.OrderService;
import com.jctiru.lnshop.api.service.StripeService;
import com.jctiru.lnshop.api.shared.Utils;
import com.jctiru.lnshop.api.ui.model.request.OrderRequestModel;
import com.stripe.model.Charge;
import com.stripe.model.Token;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	LightNovelRepository lightNovelRepository;

	@Autowired
	StripeService stripeService;

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	Utils utils;

	@Transactional
	@Override
	public void createOrder(OrderRequestModel orderRequest, String email) {
		OrderEntity order = new OrderEntity();
		order.setOrderItems(new ArrayList<>());

		BigDecimal totalAmount = BigDecimal.ZERO;

		for (Map.Entry<String, Integer> item : orderRequest.getCartItems().entrySet()) {
			OrderItemEntity orderItem = new OrderItemEntity();
			LightNovelEntity lightNovel = lightNovelRepository.findByLightNovelId(item.getKey());
			orderItem.setLightNovel(lightNovel);
			orderItem.setQuantity(item.getValue());
			orderItem.setPrice(lightNovel.getPrice());
			orderItem.setSubtotal(lightNovel.getPrice().multiply(new BigDecimal(item.getValue())));

			order.addOrderItem(orderItem);

			totalAmount = totalAmount.add(orderItem.getSubtotal());
		}

		Token token = stripeService.retrieveToken(orderRequest.getStripeTokenId());
		Charge charge = stripeService.createCharge(token, totalAmount, email, orderRequest.getAddressArgs());

		Map<String, String> chargeMetaData = charge.getMetadata();
		ShippingAddressEntity shippingAddress = new ShippingAddressEntity();
		shippingAddress.setName(chargeMetaData.get("shipping_name"));
		shippingAddress.setCountry(chargeMetaData.get("shipping_address_country"));
		shippingAddress.setZip(chargeMetaData.get("shipping_address_zip"));
		shippingAddress.setLine(chargeMetaData.get("shipping_address_line1"));
		shippingAddress.setCity(chargeMetaData.get("shipping_address_city"));

		order.setOrderId(utils.generatePublicEntityId(Utils.EntityType.ORDER));
		order.setStripeTokenId(token.getId());
		order.setStripeChargeId(charge.getId());
		order.setShippingAddress(shippingAddress);
		order.setTotal(totalAmount);

		UserEntity user = userRepository.findUserByEmail(email);
		user.addOrder(order);

		orderRepository.save(order);
	}

}
