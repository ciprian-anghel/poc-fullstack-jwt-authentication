package com.springsecurityandangular.springsecurityandangular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityandangular.springsecurityandangular.config.UserAuthProvider;
import com.springsecurityandangular.springsecurityandangular.dto.CredentialsDto;
import com.springsecurityandangular.springsecurityandangular.dto.UserDto;
import com.springsecurityandangular.springsecurityandangular.services.UserService;

@RestController
@RequestMapping("/api")
public class AuthController {
	
	private UserService userService;
	private UserAuthProvider userAuthProvider;
	
	public AuthController(UserService userService, UserAuthProvider userAuthProvider) {
		this.userService = userService;
		this.userAuthProvider = userAuthProvider;
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {		
		UserDto user = userService.login(credentialsDto);
		user.setToken(userAuthProvider.createToken(user));
		return ResponseEntity.ok(user);
	}
 
}
