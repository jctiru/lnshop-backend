package com.jctiru.lnshop.api.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;
import com.jctiru.lnshop.api.service.AmazonSESClientService;
import com.jctiru.lnshop.api.shared.AmazonSESFrom;
import com.jctiru.lnshop.api.shared.EmailTemplates;
import com.jctiru.lnshop.api.shared.dto.OrderDto;

@Service
public class AmazonSESClientServiceImpl implements AmazonSESClientService {

	@Autowired
	AmazonSimpleEmailService amazonSES;

	@Async
	@Override
	public void sendEmailVerification(String destinationEmail, String emailVerificationToken) {
		// Set sending email
		String source = AmazonSESFrom.LNSHOP.getFrom();

		// Set recipient address
		Destination destination = new Destination().withToAddresses(destinationEmail);

		// Create subject and body of message
		Content subject = new Content().withCharset("UTF-8").withData(EmailTemplates.getEmailVerificationSubject());
		List<String> htmlAndTextBody = EmailTemplates.getEmailVerificationHtmlAndTextBody(emailVerificationToken);
		Content htmlBody = new Content().withCharset("UTF-8").withData(htmlAndTextBody.get(0));
		Content textBody = new Content().withCharset("UTF-8").withData(htmlAndTextBody.get(1));
		Body body = new Body().withHtml(htmlBody).withText(textBody);

		// Create message with specified subject and body
		Message message = new Message().withSubject(subject).withBody(body);

		// Assemble email
		SendEmailRequest request = new SendEmailRequest()
				.withSource(source)
				.withDestination(destination)
				.withMessage(message);

		// Send the email
		amazonSES.sendEmail(request);
	}

	@Async
	@Override
	public void sendOrderConfirmation(String destinationEmail, OrderDto order) {
		// Set sending email
		String source = AmazonSESFrom.LNSHOP.getFrom();

		// Set recipient address
		Destination destination = new Destination().withToAddresses(destinationEmail);

		// Create subject and body of message
		Content subject = new Content().withCharset("UTF-8").withData(EmailTemplates.getOrderConfirmationSubject());
		List<String> htmlAndTextBody = EmailTemplates.getOrderConfirmationHtmlAndTextBody(order);
		Content htmlBody = new Content().withCharset("UTF-8").withData(htmlAndTextBody.get(0));
		Content textBody = new Content().withCharset("UTF-8").withData(htmlAndTextBody.get(1));
		Body body = new Body().withHtml(htmlBody).withText(textBody);

		// Create message with specified subject and body
		Message message = new Message().withSubject(subject).withBody(body);

		// Assemble email
		SendEmailRequest request = new SendEmailRequest()
				.withSource(source)
				.withDestination(destination)
				.withMessage(message);

		// Send the email
		amazonSES.sendEmail(request);
	}

}
