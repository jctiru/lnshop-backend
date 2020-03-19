package com.jctiru.lnshop.api.security;

public final class SecurityConstants {

	private SecurityConstants() {
	}

	public static final long EXPIRATION_TIME = 864000000L; // 10 days
	public static final long EMAIL_VERIFICATION_TOKEN_EXPIRATION_TIME = 86400000L; // 1 day
	public static final long PASSWORD_RESET_TOKEN_EXPIRATION_TIME = 3600000L; // 1 hour
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_CLAIMS = "Authority";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";
	public static final String SIGN_IN_URL = "/users/login";
	public static final String EMAIL_VERIFICATION_URL = "/users/email-verification";
	public static final String PASSWORD_RESET_REQUEST_URL = "/users/password-reset-request";

}
