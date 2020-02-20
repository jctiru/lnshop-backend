package com.jctiru.lnshop.api.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jctiru.lnshop.api.service.StripeService;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;

@Service
public class StripeServiceImpl implements StripeService {

	@Value("${app.stripe.api.secret-key}")
	private String apiSecretKey;

	@PostConstruct
	public void init() {
		Stripe.apiKey = apiSecretKey;
	}

	@Override
	public Charge createCharge(String token, BigDecimal amount, String email) {
		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("source", token);
		chargeParams.put("currency", "USD");
		chargeParams.put("amount", amount.multiply(new BigDecimal(100)).intValueExact());
		chargeParams.put("receipt_email", email);

		try {
			return Charge.create(chargeParams);
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
	}

}
