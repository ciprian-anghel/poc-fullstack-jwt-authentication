package com.springsecurityandangular.springsecurityandangular.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.springsecurityandangular.springsecurityandangular.entities.User;

import jakarta.annotation.PostConstruct;

//TODO. Convert to interface and extend JpaRepository<User, Long>
@Repository
public class UserRepository {
	
	private List<User> users = new ArrayList<>();
	
	//TODO: Remove
	@PostConstruct
	private void loadUsers() {
		users.add(new User(1L, "user", "power", "user", "$2a$10$pNu5jLMm4RpCybFt4cXYS.9sstKB8ryzR2Ze6iTvoW0VFf7sCWQCa"));
		users.add(new User(2L, "admin", "power", "admin", "$2a$10$pNu5jLMm4RpCybFt4cXYS.9sstKB8ryzR2Ze6iTvoW0VFf7sCWQCa"));
		
	}
	
	public Optional<User> findByLogin(String login) {
		return users.stream().filter(f -> f.getLogin().equalsIgnoreCase(login)).findFirst();
	}
}
