package com.jctiru.lnshop.api.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jctiru.lnshop.api.AppPropertiesFile;
import com.jctiru.lnshop.api.io.entity.RoleEntity;
import com.jctiru.lnshop.api.service.UserService;
import com.jctiru.lnshop.api.shared.dto.UserDto;
import com.jctiru.lnshop.api.ui.model.request.UserLoginRequestModel;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	UserService userService;

	@Autowired
	AppPropertiesFile appProperties;

	@PostConstruct
	private void postConstruct() {
		this.setFilterProcessesUrl(SecurityConstants.SIGN_IN_URL);
	}

	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);

	}

	@Override
	@Autowired
	public void setAuthenticationFailureHandler(AuthenticationFailureHandler failureHandler) {
		super.setAuthenticationFailureHandler(failureHandler);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			UserLoginRequestModel creds = new ObjectMapper().readValue(req.getInputStream(),
					UserLoginRequestModel.class);

			return this.getAuthenticationManager().authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {
		User user = (User) auth.getPrincipal();

		List<String> authorityList = user.getAuthorities()
				.stream()
				.map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		String token = Jwts.builder()
				.claim(SecurityConstants.TOKEN_CLAIMS, authorityList)
				.setSubject(user.getUsername())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, appProperties.getTokenSecret())
				.compact();

		UserDto userDto = userService.getUser(user.getUsername());

		res.setContentType("application/json");
		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.getWriter().append(jsonResponse(token, userDto.getUserId(), userDto.getRoles(),
				userDto.getFirstName(), userDto.getLastName()));
	}

	private String jsonResponse(String token, String userId, Set<RoleEntity> roles, String firstName, String lastName) {
		String role = roles.iterator().next().getName();

		return "{\"token\": \"" + token + "\", "
				+ "\"userId\": \"" + userId + "\", "
				+ "\"role\": \"" + role + "\", "
				+ "\"firstName\": \"" + firstName + "\", "
				+ "\"lastName\": \"" + lastName + "\"}";
	}

}
