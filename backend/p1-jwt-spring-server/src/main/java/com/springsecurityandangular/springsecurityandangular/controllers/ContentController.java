package com.springsecurityandangular.springsecurityandangular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityandangular.springsecurityandangular.dto.Resource;

@RestController
@RequestMapping("/api")
public class ContentController {

	@GetMapping("/resource")
	public ResponseEntity<Resource> home() {
		return ResponseEntity.ok(new Resource(1, "I'm an user!"));
	}
	
}
