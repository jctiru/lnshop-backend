package com.jctiru.lnshop.api.shared;

import java.security.SecureRandom;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jctiru.lnshop.api.AppPropertiesFile;
import com.jctiru.lnshop.api.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class Utils {

	@Autowired
	AppPropertiesFile appProperties;

	private static AppPropertiesFile staticAppProperties;

	public enum EntityType {
		USER, LIGHTNOVEL, GENRE, ORDER
	}

	private final Random random = new SecureRandom();
	private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String LOWER = UPPER.toLowerCase(Locale.ROOT);
	private static final String DIGITS = "0123456789";
	private static final String ALPHANUM = UPPER + LOWER + DIGITS;

	@PostConstruct
	public void init() {
		Utils.staticAppProperties = appProperties;
	}

	public String generatePublicEntityId(EntityType entityType) {
		switch (entityType) {
		case USER:
		case LIGHTNOVEL:
		case ORDER:
			return generateRandomString(50);
		case GENRE:
			return generateRandomString(10);
		default:
			return null;
		}
	}

	private String generateRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			returnValue.append(ALPHANUM.charAt(random.nextInt(ALPHANUM.length())));
		}

		return returnValue.toString();
	}

	public static boolean hasTokenExpired(String token) {
		Claims claims = Jwts.parser().setSigningKey(staticAppProperties.getTokenSecret()).parseClaimsJws(token)
				.getBody();
		Date tokenExpirationDate = claims.getExpiration();
		Date todayDate = new Date();

		return tokenExpirationDate.before(todayDate);
	}

	public static String generateEmailVerificationToken(String userId) {
		return Jwts.builder().setSubject(userId)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, staticAppProperties.getTokenSecret()).compact();

	}

}
