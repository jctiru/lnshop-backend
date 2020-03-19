package com.jctiru.lnshop.api.service;

import com.jctiru.lnshop.api.shared.dto.OrderDto;

public interface AmazonSESClientService {

	public void sendEmailVerification(String destinationEmail, String emailVerificationToken);

	public void sendOrderConfirmation(String destinationEmail, OrderDto order);

	public void sendPasswordResetRequest(String destinationEmail, String firstName, String passwordResetToken);

}
