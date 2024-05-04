package com.springsecurityandangular.springsecurityandangular.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurityandangular.springsecurityandangular.dto.Resource;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@GetMapping("/resource")
	public ResponseEntity<Resource> home() {
		return ResponseEntity.ok(new Resource(2, "I'm an admin!"));
	}
	
}
