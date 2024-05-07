package com.springsecurityandangular.springsecurityandangular.mappers;

import org.springframework.stereotype.Component;

import com.springsecurityandangular.springsecurityandangular.dto.RegisterDto;
import com.springsecurityandangular.springsecurityandangular.dto.UserDto;
import com.springsecurityandangular.springsecurityandangular.entities.User;

@Component
public class UserMapper {

	public UserDto toUserDto(User user) {
		return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getLogin(), "token");
	}
	
	public User registerToUser(RegisterDto user) {
		return new User(0L, user.lastName(), user.firstName(), user.login(), null);		
	}
	
}
