package com.jctiru.lnshop.api.shared;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jctiru.lnshop.api.AppPropertiesFile;
import com.jctiru.lnshop.api.io.entity.OrderItemEntity;
import com.jctiru.lnshop.api.shared.dto.OrderDto;

@Component
public class EmailTemplates {

	@Autowired
	AppPropertiesFile appProperties;

	private static AppPropertiesFile staticAppProperties;

	private static final String EMAIL_VERIFICATION_SUBJECT = "Email verification for LNShop";
	private static final String EMAIL_VERIFICATION_HTML_BODY = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><meta name=\"viewport\" content=\"width=device-width\" style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><title style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\">LNShop</title></head><body bgcolor=\"#FFFFFF\" style=\"-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;height:100%;margin:0;padding:0;width:100%!important\"><style>@media only screen and (max-width:600px){a[class=btn]{display:block!important;margin-bottom:10px!important;background-image:none!important;margin-right:0!important}div[class=column]{width:auto!important;float:none!important}table.social div[class=column]{width:auto!important}}</style><table class=\"body-wrap\" style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0;width:100%\"><tbody><tr style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><td style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"></td><td class=\"container\" bgcolor=\"#DCDCDC\" style=\"clear:both!important;display:block!important;font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0 auto!important;max-width:600px!important;padding:0\"><div class=\"content\" style=\"display:block;font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0 auto;max-width:600px;padding:15px\"><table style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0;width:100%\"><tbody><tr style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><td style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><h3 style=\"color:#000;font-family:HelveticaNeue-Light,'Helvetica Neue Light','Helvetica Neue',Helvetica,Arial,'Lucida Grande',sans-serif;font-size:27px;font-weight:500;line-height:1.1;margin:0;margin-bottom:15px;padding:0\">Hi!</h3><p class=\"lead\" style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;font-size:17px;font-weight:400;line-height:1.6;margin:0;margin-bottom:10px;padding:0\">Thank you for registering with LNShop!</p><p style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;font-size:14px;font-weight:400;line-height:1.6;margin:0;margin-bottom:10px;padding:0\">You are one step away from registering with us! Please click the link below! Link is valid for 1 day only...</p><p class=\"callout\" style=\"background-color:#ECF8FF;font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;font-size:14px;font-weight:400;line-height:1.6;margin:0;margin-bottom:15px;padding:15px\">Please click the link next to me! <a href=\"$emailVerificationLink\" style=\"color:#2BA6CB;font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;font-weight:700;margin:0;padding:0\">Click it! »</a></p></td></tr></tbody></table></div></td><td style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"></td></tr></tbody></table></body></html>";
	private static final String EMAIL_VERIFICATION_TEXT_BODY = "Hi! Thank you for registering with LNShop! You are one step away from registering with us! Please click the link below! Link is valid for 1 day only... Please click the link next to me! $emailVerificationLink";
	private static final String ORDER_CONFIRMATION_SUBJECT = "Order Summary for LNShop";
	private static final String ORDER_ITEM_HTML_SNIPPET = "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" role=\"module\" data-type=\"columns\" style=\"padding:20px 20px 0px 30px;\" bgcolor=\"#FFFFFF\"> <tbody> <tr role=\"module-content\"> <td height=\"100%\" valign=\"top\"> <table class=\"column\" width=\"137\" style=\"width:137px; border-spacing:0; border-collapse:collapse; margin:0px 0px 0px 0px;\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" border=\"0\" bgcolor=\"\" > <tbody> <tr> <td style=\"padding:0px;margin:0px;border-spacing:0;\"> <table class=\"wrapper\" role=\"module\" data-type=\"image\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed;\" data-muid=\"239f10b7-5807-4e0b-8f01-f2b8d25ec9d7\" > <tbody> <tr> <td style=\"font-size:6px; line-height:10px; padding:0px 0px 0px 0px;\" valign=\"top\" align=\"left\" > <a href=\"$orderItemLink\"> <img class=\"max-width\" border=\"0\" style=\"display:block; color:#000000; text-decoration:none; font-family:Helvetica, arial, sans-serif; font-size:16px;\" width=\"104\" alt=\"\" data-proportionally-constrained=\"true\" data-responsive=\"false\" src=\"$orderItemImageUrl\"/> </a> </td></tr></tbody> </table> </td></tr></tbody> </table> <table class=\"column\" width=\"137\" style=\"width:137px; border-spacing:0; border-collapse:collapse; margin:0px 0px 0px 0px;\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" border=\"0\" bgcolor=\"\" > <tbody> <tr> <td style=\"padding:0px;margin:0px;border-spacing:0;\"> <table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout: fixed;\" data-muid=\"f404b7dc-487b-443c-bd6f-131ccde745e2\" > <tbody> <tr> <td style=\"padding:18px 0px 18px 0px; line-height:22px; text-align:inherit;\" height=\"100%\" valign=\"top\" bgcolor=\"\" role=\"module-content\" > <div> <div style=\"font-family: inherit; text-align: inherit\" > <a href=\"$orderItemLink\"> <span style=\"color: #000000\" >$orderItemName</span ></a > </div><div style=\"font-family: inherit; text-align: inherit\" > <br/> </div><div style=\"font-family: inherit; text-align: inherit\" > <span style=\"color: #01579b\" >$orderItemPrice x $orderItemQuantity</span > </div><div></div></div></td></tr></tbody> </table> </td></tr></tbody> </table> <table width=\"137\" style=\"width:137px; border-spacing:0; border-collapse:collapse; margin:0px 0px 0px 0px;\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" border=\"0\" bgcolor=\"\" class=\"column column-2\" > <tbody> <tr> <td style=\"padding:0px;margin:0px;border-spacing:0;\"></td></tr></tbody> </table> <table width=\"137\" style=\"width:137px; border-spacing:0; border-collapse:collapse; margin:0px 0px 0px 0px;\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" border=\"0\" bgcolor=\"\" class=\"column column-3\" > <tbody> <tr> <td style=\"padding:0px;margin:0px;border-spacing:0;\"></td></tr></tbody> </table> </td></tr></tbody></table>";
	private static final String ORDER_ITEM_TEXT_SNIPPET = "$orderItemName ($orderItemLink) \\r\\n $orderItemPrice x $orderItemQuantity \\r\\n\\r\\n";
	private static final String ORDER_CONFIRMATION_HTML_BODY = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\"><meta name=\"viewport\" content=\"width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=Edge\"><!--[if (gte mso 9)|(IE)]> <xml> <o:OfficeDocumentSettings> <o:AllowPNG/> <o:PixelsPerInch>96</o:PixelsPerInch> </o:OfficeDocumentSettings> </xml><![endif]--><!--[if (gte mso 9)|(IE)]> <style type=\"text/css\"> body{width: 600px; margin: 0 auto;}table{border-collapse: collapse;}table, td{mso-table-lspace: 0pt; mso-table-rspace: 0pt;}img{-ms-interpolation-mode: bicubic;}</style><![endif]--><style></style><link href=\"https://fonts.googleapis.com/css?family=Viga&display=swap\" rel=\"stylesheet\" type=\"text/css\"/></head><body style=\"color:#000;font-family:Viga,sans-serif;font-size:14px\"><style type=\"text/css\">@media screen and (max-width:480px){.footer .rightColumnContent,.preheader .rightColumnContent{text-align:left!important}.footer .rightColumnContent div,.footer .rightColumnContent span,.preheader .rightColumnContent div,.preheader .rightColumnContent span{text-align:left!important}.preheader .leftColumnContent,.preheader .rightColumnContent{font-size:80%!important;padding:5px 0}table.wrapper-mobile{width:100%!important;table-layout:fixed}img.max-width{height:auto!important;max-width:100%!important}a.bulletproof-button{display:block!important;width:auto!important;font-size:80%;padding-left:0!important;padding-right:0!important}.columns{width:100%!important}.column{display:block!important;width:100%!important;padding-left:0!important;padding-right:0!important;margin-left:0!important;margin-right:0!important}}</style><center class=\"wrapper\" data-link-color=\"#1188E6\" data-body-style=\"font-size:14px; font-family:inherit; color:#000000; background-color:#f0f0f0;\"><div class=\"webkit\" style=\"font-family:inherit;font-size:14px\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" width=\"100%\" class=\"wrapper\" bgcolor=\"#f0f0f0\" style=\"-moz-text-size-adjust:100%;-ms-text-size-adjust:100%;-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:100%;table-layout:fixed;width:100%!important\"><tbody><tr><td valign=\"top\" bgcolor=\"#f0f0f0\" width=\"100%\"><table width=\"100%\" role=\"content-container\" class=\"outer\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tbody><tr><td width=\"100%\"><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tbody><tr><td><!--[if mso]> <center> <table><tr><td width=\"600\"><![endif]--><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:100%;max-width:600px\" align=\"center\"><tbody><tr><td role=\"modules-container\" style=\"padding:0;color:#000;text-align:left\" bgcolor=\"#ffffff\" width=\"100%\" align=\"left\"><table class=\"module preheader preheader-hide\" role=\"module\" data-type=\"preheader\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"display:none!important;mso-hide:all;visibility:hidden;opacity:0;color:transparent;height:0;width:0\"><tbody><tr><td role=\"module-content\"><p style=\"font-family:inherit;font-size:14px;margin:0;padding:0\"></p></td></tr></tbody></table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" role=\"module\" data-type=\"columns\" style=\"padding:30px 20px 40px 30px\" bgcolor=\"#455A64\"><tbody><tr role=\"module-content\"><td height=\"100%\" valign=\"top\"><table class=\"column\" width=\"550\" style=\"width:550px;border-spacing:0;border-collapse:collapse;margin:0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" border=\"0\" bgcolor=\"\"><tbody><tr><td style=\"padding:0;margin:0;border-spacing:0\"><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout:fixed\" data-muid=\"1995753e-0c64-4075-b4ad-321980b82dfe\"><tbody><tr><td style=\"padding:0 0 18px 0;line-height:36px;text-align:inherit\" height=\"100%\" valign=\"top\" bgcolor=\"\" role=\"module-content\"><div style=\"font-family:inherit;font-size:14px\"><div style=\"font-family:inherit;font-size:14px;text-align:inherit\"><span style=\"color:#fff;font-size:40px;font-family:inherit\"><strong>LNSHOP</strong></span></div><div style=\"font-family:inherit;font-size:14px\"></div></div></td></tr></tbody></table><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout:fixed\" data-muid=\"1995753e-0c64-4075-b4ad-321980b82dfe\"><tbody><tr><td style=\"padding:20px 0 18px 0;line-height:36px;text-align:inherit\" height=\"100%\" valign=\"top\" bgcolor=\"\" role=\"module-content\"><div style=\"font-family:inherit;font-size:14px\"><div style=\"font-family:inherit;font-size:14px;text-align:inherit\"><span style=\"color:#fff;font-size:35px;font-family:inherit\">Thank you for your order!</span></div><div style=\"font-family:inherit;font-size:14px\"></div></div></td></tr></tbody></table><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout:fixed\" data-muid=\"2ffbd984-f644-4c25-9a1e-ef76ac62a549\"><tbody><tr><td style=\"padding:18px 20px 20px 0;line-height:24px;text-align:inherit\" height=\"100%\" valign=\"top\" bgcolor=\"\" role=\"module-content\"><div style=\"font-family:inherit;font-size:14px\"><div style=\"font-family:inherit;font-size:14px;text-align:inherit\"><span style=\"color:#000000;font-size:24px\">Hope you have a good time reading!</span></div></div></td></tr></tbody></table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"module\" data-role=\"module-button\" data-type=\"button\" role=\"module\" style=\"table-layout:fixed\" width=\"100%\" data-muid=\"69fc33ea-7c02-45ed-917a-b3b8a6866e89\"><tbody><tr><td align=\"left\" bgcolor=\"\" class=\"outer-td\" style=\"padding:0\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"wrapper-mobile\" style=\"text-align:center\"><tbody><tr><td align=\"center\" bgcolor=\"#000000\" class=\"inner-td\" style=\"border-radius:6px;font-size:16px;text-align:left;background-color:inherit\"><a href=\"$orderLink\" style=\"background-color:#000;border:1px solid #000;border-color:#000;border-radius:0;border-style:solid;border-width:1px;color:#fff;display:inline-block;font-family:inherit;font-size:18px;font-weight:400;letter-spacing:0;line-height:normal;padding:12px 18px 12px 18px;text-align:center;text-decoration:none\" target=\"_blank\">View Order</a></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout:fixed\" data-muid=\"8b5181ed-0827-471c-972b-74c77e326e3d\"><tbody><tr><td style=\"padding:30px 20px 18px 30px;line-height:22px;text-align:inherit\" height=\"100%\" valign=\"top\" bgcolor=\"\" role=\"module-content\"><div style=\"font-family:inherit;font-size:14px\"><div style=\"font-family:inherit;font-size:14px;text-align:inherit\"><span style=\"color:#01579b;font-size:24px\">Order Summary</span></div><div style=\"font-family:inherit;font-size:14px\"></div></div></td></tr></tbody></table><table class=\"module\" role=\"module\" data-type=\"divider\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout:fixed\" data-muid=\"f7373f10-9ba4-4ca7-9a2e-1a2ba700deb9\"><tbody><tr><td style=\"padding:0 30px 0 30px\" role=\"module-content\" height=\"100%\" valign=\"top\" bgcolor=\"\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" height=\"3px\" style=\"line-height:3px;font-size:3px\"><tbody><tr><td style=\"padding:0 0 3px 0\" bgcolor=\"#e7e7e7\"></td></tr></tbody></table></td></tr></tbody></table><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout:fixed\" data-muid=\"264ee24b-c2b0-457c-a9c1-d465879f9935\"><tbody><tr><td style=\"padding:18px 20px 18px 30px;line-height:22px;text-align:inherit\" height=\"100%\" valign=\"top\" bgcolor=\"\" role=\"module-content\"><div style=\"font-family:inherit;font-size:14px\"><div style=\"font-family:inherit;font-size:14px;text-align:inherit\"><strong>Order Id: $orderId</strong></div><div style=\"font-family:inherit;font-size:14px;text-align:inherit\"><span style=\"color:#01579b\"><strong>Order Date: $orderDate</strong></span></div><div style=\"font-family:inherit;font-size:14px;text-align:inherit\"><br></div><div style=\"color:#000000;font-family:inherit;font-size:14px;text-align:inherit\"><strong>Shipping Address:</strong></div><div style=\"color:#000000;font-family:inherit;font-size:14px;text-align:inherit\">$orderShippingAddressLine</div><div style=\"color:#000000;font-family:inherit;font-size:14px;text-align:inherit\">$orderShippingAddressCity, $orderShippingAddressZip</div><div style=\"color:#000000;font-family:inherit;font-size:14px;text-align:inherit\">$orderShippingAddressCountry</div></div></td></tr></tbody></table>$orderItems<table class=\"module\" role=\"module\" data-type=\"divider\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout:fixed\" data-muid=\"f7373f10-9ba4-4ca7-9a2e-1a2ba700deb9.1\"><tbody><tr><td style=\"padding:20px 30px 0 30px\" role=\"module-content\" height=\"100%\" valign=\"top\" bgcolor=\"\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" height=\"3px\" style=\"line-height:3px;font-size:3px\"><tbody><tr><td style=\"padding:0 0 3px 0\" bgcolor=\"E7E7E7\"></td></tr></tbody></table></td></tr></tbody></table><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout:fixed\" data-muid=\"264ee24b-c2b0-457c-a9c1-d465879f9935.1\"><tbody><tr><td style=\"padding:18px 20px 30px 30px;line-height:22px;text-align:inherit\" height=\"100%\" valign=\"top\" bgcolor=\"\" role=\"module-content\"><div style=\"font-family:inherit;font-size:14px\"><div style=\"font-family:inherit;font-size:14px;text-align:inherit\"><br><strong>Grand Total:</strong></div><div style=\"font-family:inherit;font-size:14px;text-align:inherit\"><br></div><div style=\"font-family:inherit;font-size:14px;text-align:inherit\"><span style=\"color:#01579b;font-size:32px;font-family:inherit\">$orderTotal</span></div><div style=\"font-family:inherit;font-size:14px\"></div></div></td></tr></tbody></table><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" align=\"center\" width=\"100%\" role=\"module\" data-type=\"columns\" style=\"padding:0 20px 0 20px\" bgcolor=\"#455A64\"><tbody><tr role=\"module-content\"><td height=\"100%\" valign=\"top\"><table class=\"column\" width=\"140\" style=\"width:140px;border-spacing:0;border-collapse:collapse;margin:0\" cellpadding=\"0\" cellspacing=\"0\" align=\"left\" border=\"0\" bgcolor=\"\"><tbody><tr><td style=\"padding:0;margin:0;border-spacing:0\"><table class=\"module\" role=\"module\" data-type=\"text\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"table-layout:fixed\" data-muid=\"9d43ffa1-8e24-438b-9484-db553cf5b092\"><tbody><tr><td style=\"padding:18px 0 18px 0;line-height:22px;text-align:inherit\" height=\"100%\" valign=\"top\" bgcolor=\"\" role=\"module-content\"></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table><!--[if mso]> </td></tr></table> </center><![endif]--></td></tr></tbody></table></td></tr></tbody></table></td></tr></tbody></table></div></center></body></html>";
	private static final String ORDER_CONFIRMATION_TEXT_BODY = " \\r\\n\\r\\n LNSHOP \\r\\n\\r\\n Thank you for your order! \\r\\n\\r\\n Hope you have a good time reading! \\r\\n\\r\\n View Order ($orderLink) \\r\\n\\r\\n Order Summary \\r\\n\\r\\n ORDER ID: $orderId \\r\\n ORDER DATE: $orderDate \\r\\n\\r\\n SHIPPING ADDRESS: \\r\\n $orderShippingAddressLine \\r\\n $orderShippingAddressCity, $orderShippingAddressZip \\r\\n $orderShippingAddressCountry \\r\\n\\r\\n $orderItemsText \\r\\n\\r\\nGRAND TOTAL: \\r\\n\\r\\n $orderTotal \\r\\n\\r\\n ";

	@PostConstruct
	public void init() {
		EmailTemplates.staticAppProperties = appProperties;
	}

	public static String getEmailVerificationSubject() {
		return EMAIL_VERIFICATION_SUBJECT;
	}

	public static List<String> getEmailVerificationHtmlAndTextBody(String emailVerificationToken) {
		List<String> emailVerificationHtmlAndTextBody = new ArrayList<>();
		String emailVerificationLink = staticAppProperties.getAppFrontendUrl() + "/email-verification?token="
				+ emailVerificationToken;
		emailVerificationHtmlAndTextBody
				.add(EMAIL_VERIFICATION_HTML_BODY.replace("$emailVerificationLink", emailVerificationLink));
		emailVerificationHtmlAndTextBody
				.add(EMAIL_VERIFICATION_TEXT_BODY.replace("$emailVerificationLink", emailVerificationLink));

		return emailVerificationHtmlAndTextBody;
	}

	public static String getOrderConfirmationSubject() {
		return ORDER_CONFIRMATION_SUBJECT;
	}

	public static List<String> getOrderConfirmationHtmlAndTextBody(OrderDto order) {
		List<String> orderConfirmationHtmlAndTextBody = new ArrayList<>();
		StringBuilder orderHtmlBody = new StringBuilder(ORDER_CONFIRMATION_HTML_BODY);
		StringBuilder orderTextBody = new StringBuilder(ORDER_CONFIRMATION_TEXT_BODY);
		StringBuilder orderItemsHtmlSnippet = new StringBuilder();
		StringBuilder orderItemsTextSnippet = new StringBuilder();

		for (OrderItemEntity orderItem : order.getOrderItems()) {
			StringBuilder orderItemHtmlSnippet = new StringBuilder(ORDER_ITEM_HTML_SNIPPET);
			StringBuilder orderItemTextSnippet = new StringBuilder(ORDER_ITEM_TEXT_SNIPPET);

			Map<String, String> map = new HashMap<>();
			map.put("$orderItemLink", staticAppProperties.getAppFrontendUrl() + "/novels/show/"
					+ orderItem.getLightNovel().getLightNovelId());
			map.put("$orderItemImageUrl", orderItem.getLightNovel().getImageUrl());
			map.put("$orderItemName", orderItem.getLightNovel().getTitle());
			map.put("$orderItemPrice", "$" + orderItem.getPrice().toPlainString());
			map.put("$orderItemQuantity", Integer.toString(orderItem.getQuantity()));

			for (Map.Entry<String, String> val : map.entrySet()) {
				EmailTemplates.replace(orderItemHtmlSnippet, val.getKey(), val.getValue());
				EmailTemplates.replace(orderItemTextSnippet, val.getKey(), val.getValue());
			}

			orderItemsHtmlSnippet.append(orderItemHtmlSnippet);
			orderItemsTextSnippet.append(orderItemTextSnippet);
		}

		Map<String, String> map = new HashMap<>();
		map.put("$orderLink", staticAppProperties.getAppFrontendUrl() + "/profile/orders/" + order.getOrderId());
		map.put("$orderId", order.getOrderId());
		map.put("$orderDate", order.getCreateDateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
		map.put("$orderShippingAddressLine", order.getShippingAddress().getLine());
		map.put("$orderShippingAddressCity", order.getShippingAddress().getCity());
		map.put("$orderShippingAddressZip", order.getShippingAddress().getZip());
		map.put("$orderShippingAddressCountry", order.getShippingAddress().getCountry());
		map.put("$orderTotal", "$" + order.getTotal().toPlainString());
		map.put("$orderItems", orderItemsHtmlSnippet.toString());
		map.put("$orderItemsText", orderItemsTextSnippet.toString());

		for (Map.Entry<String, String> val : map.entrySet()) {
			EmailTemplates.replace(orderHtmlBody, val.getKey(), val.getValue());
			EmailTemplates.replace(orderTextBody, val.getKey(), val.getValue());
		}

		orderConfirmationHtmlAndTextBody.add(orderHtmlBody.toString());
		orderConfirmationHtmlAndTextBody.add(orderTextBody.toString());

		return orderConfirmationHtmlAndTextBody;
	}

	private static StringBuilder replace(StringBuilder originalString, String stringToBeReplaced,
			String stringToReplaceWith) {
		int indexOfStringToBeReplaced = -1;

		while ((indexOfStringToBeReplaced = originalString.indexOf(stringToBeReplaced)) >= 0) {
			originalString.replace(originalString.indexOf(stringToBeReplaced),
					originalString.indexOf(stringToBeReplaced) + stringToBeReplaced.length(), stringToReplaceWith);
		}

		return originalString;
	}

}
