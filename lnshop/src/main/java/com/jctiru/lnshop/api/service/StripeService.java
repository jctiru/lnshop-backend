package com.jctiru.lnshop.api.service;

import java.math.BigDecimal;

import com.stripe.model.Charge;
import com.stripe.model.Token;

public interface StripeService {

	Charge createCharge(Token token, BigDecimal amount, String email, Object addressArgs);

	Token retrieveToken(String tokenId);

}
