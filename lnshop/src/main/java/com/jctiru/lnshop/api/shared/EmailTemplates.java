package com.jctiru.lnshop.api.shared;

import org.springframework.stereotype.Component;

@Component
public class EmailTemplates {

	private static final String EMAIL_VERIFICATION_SUBJECT = "Email verification for LNShop";
	private static final String EMAIL_VERIFICATION_HTML_BODY = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><meta name=\"viewport\" content=\"width=device-width\" style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><title style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\">LNShop</title></head><body bgcolor=\"#FFFFFF\" style=\"-webkit-font-smoothing:antialiased;-webkit-text-size-adjust:none;font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;height:100%;margin:0;padding:0;width:100%!important\"><style>@media only screen and (max-width:600px){a[class=btn]{display:block!important;margin-bottom:10px!important;background-image:none!important;margin-right:0!important}div[class=column]{width:auto!important;float:none!important}table.social div[class=column]{width:auto!important}}</style><table class=\"body-wrap\" style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0;width:100%\"><tbody><tr style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><td style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"></td><td class=\"container\" bgcolor=\"#DCDCDC\" style=\"clear:both!important;display:block!important;font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0 auto!important;max-width:600px!important;padding:0\"><div class=\"content\" style=\"display:block;font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0 auto;max-width:600px;padding:15px\"><table style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0;width:100%\"><tbody><tr style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><td style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"><h3 style=\"color:#000;font-family:HelveticaNeue-Light,'Helvetica Neue Light','Helvetica Neue',Helvetica,Arial,'Lucida Grande',sans-serif;font-size:27px;font-weight:500;line-height:1.1;margin:0;margin-bottom:15px;padding:0\">Hi!</h3><p class=\"lead\" style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;font-size:17px;font-weight:400;line-height:1.6;margin:0;margin-bottom:10px;padding:0\">Thank you for registering with LNShop!</p><p style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;font-size:14px;font-weight:400;line-height:1.6;margin:0;margin-bottom:10px;padding:0\">You are one step away from registering with us! Please click the link below! Link is valid for 1 day only...</p><p class=\"callout\" style=\"background-color:#ECF8FF;font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;font-size:14px;font-weight:400;line-height:1.6;margin:0;margin-bottom:15px;padding:15px\">Please click the link next to me! <a href=\"$emailVerificationLink\" style=\"color:#2BA6CB;font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;font-weight:700;margin:0;padding:0\">Click it! »</a></p></td></tr></tbody></table></div></td><td style=\"font-family:'Helvetica Neue',Helvetica,Helvetica,Arial,sans-serif;margin:0;padding:0\"></td></tr></tbody></table></body></html>";
	private static final String EMAIL_VERIFICATION_TEXT_BODY = "Hi! Thank you for registering with LNShop! You are one step away from registering with us! Please click the link below! Link is valid for 1 day only... Please click the link next to me! $emailVerificationLink";

	public static String getEmailVerificationSubject() {
		return EMAIL_VERIFICATION_SUBJECT;
	}

	public static String getEmailVerificationHtmlBody(String emailVerificationLink) {
		return EMAIL_VERIFICATION_HTML_BODY.replace("$emailVerificationLink", emailVerificationLink);
	}

	public static String getEmailVerificationTextBody(String emailVerificationLink) {
		return EMAIL_VERIFICATION_TEXT_BODY.replace("$emailVerificationLink", emailVerificationLink);
	}

}
