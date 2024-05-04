package com.springsecurityandangular.springsecurityandangular.config;

import java.util.Arrays;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	final UserAuthProvider userAuthFilter;
	
	public SecurityConfig(UserAuthProvider userAuthFilter) {
		this.userAuthFilter = userAuthFilter;
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable()) //TODO: enable it
			.addFilterBefore(new JwtAuthFilter(userAuthFilter), BasicAuthenticationFilter.class)
			.sessionManagement(customizer -> customizer.sessionCreationPolicy(
					SessionCreationPolicy.STATELESS))
			.cors(cors -> cors.configurationSource(new CustomCorsConfig()))
			.authorizeHttpRequests(req -> req
					.requestMatchers("/api/login").permitAll()
					.requestMatchers("/api/admin/**").hasRole("ADMIN")
					.anyRequest().authenticated());			
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	private class CustomCorsConfig implements CorsConfigurationSource {
    	@Override
		public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
			CorsConfiguration config = new CorsConfiguration();
			config.setAllowCredentials(true);
			config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
			config.setAllowedHeaders(Arrays.asList(
					HttpHeaders.AUTHORIZATION,
					HttpHeaders.CONTENT_TYPE,
					HttpHeaders.ACCEPT
			));
			config.setAllowedMethods(Arrays.asList(
					HttpMethod.GET.name(),
					HttpMethod.POST.name(),
					HttpMethod.PUT.name(),
					HttpMethod.DELETE.name()
			));
			config.setMaxAge(3600L);
			
	        return config;
		}
    }
	
}
