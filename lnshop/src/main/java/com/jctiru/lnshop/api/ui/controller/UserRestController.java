package com.jctiru.lnshop.api.ui.controller;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.apache.http.HttpStatus;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jctiru.lnshop.api.service.UserService;
import com.jctiru.lnshop.api.shared.RequestOperationName;
import com.jctiru.lnshop.api.shared.RequestOperationResult;
import com.jctiru.lnshop.api.shared.dto.UserDto;
import com.jctiru.lnshop.api.ui.model.request.PasswordResetRequestModel;
import com.jctiru.lnshop.api.ui.model.request.UserDetailsRequestModel;
import com.jctiru.lnshop.api.ui.model.response.OperationStatusModel;
import com.jctiru.lnshop.api.ui.model.response.UserRest;

@RestController
@RequestMapping("users")
public class UserRestController {

	@Autowired
	UserService userService;

	@Autowired
	ModelMapper modelMapper;

	@PostMapping
	public UserRest createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
		UserDto userDto = modelMapper.map(userDetails, UserDto.class);
		UserDto createdUser = userService.createUser(userDto);

		return modelMapper.map(createdUser, UserRest.class);
	}

	@GetMapping(path = "/email-verification")
	public OperationStatusModel verifyEmailToken(@RequestParam String token, HttpServletResponse response) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.VERIFY_EMAIL.name());
		boolean isVerified = userService.verifyEmailToken(token);

		if (isVerified) {
			returnValue.setOperationResult(RequestOperationResult.SUCCESS.name());
		} else {
			returnValue.setOperationResult(RequestOperationResult.ERROR.name());
			response.setStatus(HttpStatus.SC_UNPROCESSABLE_ENTITY);
		}

		return returnValue;
	}

	@PostMapping(path = "/password-reset-request")
	public OperationStatusModel requestPasswordReset(@RequestBody PasswordResetRequestModel passwordResetRequestModel) {
		OperationStatusModel returnValue = new OperationStatusModel();
		boolean operationResult = userService.requestPasswordReset(passwordResetRequestModel.getEmail());
		returnValue.setOperationName(RequestOperationName.REQUEST_PASSWORD_RESET.name());
		returnValue.setOperationResult(RequestOperationResult.ERROR.name());

		if (operationResult) {
			returnValue.setOperationResult(RequestOperationResult.SUCCESS.name());
		}

		return returnValue;
	}

	// @PreAuthorize("hasAuthority('USER')")
	@PreAuthorize("isAuthenticated()")
	@GetMapping(value = "/user-path")
	public Map<String, String> getString() {
		return Collections.singletonMap("response", "Hello User");
	}

}
