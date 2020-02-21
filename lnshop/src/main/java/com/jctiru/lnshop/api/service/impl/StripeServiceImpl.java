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
import com.stripe.model.Token;

@Service
public class StripeServiceImpl implements StripeService {

	@Value("${app.stripe.api.secret-key}")
	private String apiSecretKey;

	@PostConstruct
	public void init() {
		Stripe.apiKey = apiSecretKey;
	}

	@Override
	public Charge createCharge(Token token, BigDecimal amount, String email, Object addressArgs) {
		Map<String, Object> chargeParams = new HashMap<>();
		chargeParams.put("source", token.getId());
		chargeParams.put("currency", "USD");
		chargeParams.put("amount", amount.multiply(new BigDecimal(100)).intValueExact());
		chargeParams.put("receipt_email", email);
		chargeParams.put("statement_descriptor_suffix", "LNShop charge");
		chargeParams.put("description", "LNShop charge for " + email + ".");
		chargeParams.put("metadata", addressArgs);

		try {
			return Charge.create(chargeParams);
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Token retrieveToken(String tokenId) {
		try {
			return Token.retrieve(tokenId);
		} catch (StripeException e) {
			throw new RuntimeException(e);
		}
	}

}
