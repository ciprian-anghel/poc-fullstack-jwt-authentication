package com.springsecurityandangular.springsecurityandangular.services;

import java.nio.CharBuffer;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springsecurityandangular.springsecurityandangular.dto.CredentialsDto;
import com.springsecurityandangular.springsecurityandangular.dto.UserDto;
import com.springsecurityandangular.springsecurityandangular.entities.User;
import com.springsecurityandangular.springsecurityandangular.exceptions.AppException;
import com.springsecurityandangular.springsecurityandangular.mappers.UserMapper;
import com.springsecurityandangular.springsecurityandangular.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
	
	private static final String INVALID_USERNAME_PASSWORD = "Invalid username or password";

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;
	
	public UserService(UserRepository userRepository,
					   PasswordEncoder passwordEncoder,
					   UserMapper userMapper) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userMapper = userMapper;
	}
	
	public UserDto login(CredentialsDto credentialsDto) {
		User user = userRepository.findByLogin(credentialsDto.login())
			.orElseThrow(() -> new AppException(INVALID_USERNAME_PASSWORD, HttpStatus.UNAUTHORIZED));
		
		if (passwordEncoder.matches(
				CharBuffer.wrap(credentialsDto.password()), user.getPassword())) {
			return userMapper.toUserDto(user);
		} 
		throw new AppException(INVALID_USERNAME_PASSWORD, HttpStatus.UNAUTHORIZED);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByLogin(username)
				.orElseThrow(() -> new AppException("User not found", HttpStatus.BAD_REQUEST));
		
		return org.springframework.security.core.userdetails.User.builder()
					.username(user.getLogin())
					.password(user.getPassword())
					.roles("USER")
					.build();
	}
	
}
