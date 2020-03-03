package com.jctiru.lnshop.api.ui.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jctiru.lnshop.api.service.OrderService;
import com.jctiru.lnshop.api.shared.dto.OrderDto;
import com.jctiru.lnshop.api.shared.dto.OrderPageDto;
import com.jctiru.lnshop.api.ui.model.request.OrderRequestModel;
import com.jctiru.lnshop.api.ui.model.response.OperationStatusModel;
import com.jctiru.lnshop.api.ui.model.response.OrderDetailsRest;
import com.jctiru.lnshop.api.ui.model.response.OrderOverviewPageRest;

@RestController
@RequestMapping("orders")
public class OrderRestController {

	@Autowired
	OrderService orderService;

	@Autowired
	ModelMapper modelMapper;

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

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/admin")
	public OrderOverviewPageRest getOrders(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "8") int limit) {
		OrderPageDto orderPageDto = orderService.getOrders(page, limit);

		return modelMapper.map(orderPageDto, OrderOverviewPageRest.class);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping
	public OrderOverviewPageRest getOrders(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "8") int limit, Authentication authentication) {
		OrderPageDto orderPageDto = orderService.getOrders(page, limit, authentication.getName());

		return modelMapper.map(orderPageDto, OrderOverviewPageRest.class);
	}

	@PreAuthorize("isAuthenticated()")
	@GetMapping(path = "/{orderId}")
	public OrderDetailsRest getOrder(@PathVariable String orderId, Authentication authentication) {
		OrderDto orderDto = orderService.getOrderByOrderId(orderId, authentication);

		return modelMapper.map(orderDto, OrderDetailsRest.class);
	}

}
