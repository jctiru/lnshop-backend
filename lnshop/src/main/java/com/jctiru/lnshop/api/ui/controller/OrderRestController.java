package com.jctiru.lnshop.api.ui.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jctiru.lnshop.api.service.OrderService;
import com.jctiru.lnshop.api.ui.model.request.OrderRequestModel;
import com.jctiru.lnshop.api.ui.model.response.OperationStatusModel;

@RestController
@RequestMapping("orders")
public class OrderRestController {

	@Autowired
	OrderService orderService;

	@PreAuthorize("isAuthenticated()")
	@PostMapping
	public OperationStatusModel orderLightNovels(@Valid @RequestBody OrderRequestModel orderRequest,
			Authentication authentication) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName("PAYMENT");
		orderService.createOrder(orderRequest, authentication.getName());
		returnValue.setOperationResult("SUCCESS");

		return returnValue;
	}

}
