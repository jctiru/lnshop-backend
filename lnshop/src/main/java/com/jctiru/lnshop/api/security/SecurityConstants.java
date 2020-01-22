package com.jctiru.lnshop.api.security;

import org.springframework.beans.factory.annotation.Value;

public class SecurityConstants {

	@Value("${jwt.tokensecret}")
	private static String tokenSecret;

	public static final long EXPIRATION_TIME = 864000000L; // 10 days
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users";

	public static String getTokenSecret() {
		return tokenSecret;
	}

}
