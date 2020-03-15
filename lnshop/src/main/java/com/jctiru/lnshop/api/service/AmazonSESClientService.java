package com.jctiru.lnshop.api.service;

public interface AmazonSESClientService {

	public void sendEmailVerification(String destinationEmail, String emailVerificationToken);

}
