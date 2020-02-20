package com.jctiru.lnshop.api.service;

import java.math.BigDecimal;

import com.stripe.model.Charge;

public interface StripeService {

	Charge createCharge(String token, BigDecimal amount, String email);

}
