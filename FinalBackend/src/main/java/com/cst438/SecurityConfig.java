package com.cst438;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private AuthenticationFilter authenticationFilter;

	@Autowired
	private AuthEntryPoint exceptionHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().cors().and()
				.sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.authorizeRequests()
				.antMatchers("/api/test", "/api/search/**").permitAll() // Permit all requests to these endpoints
				// Add or remove antMatchers as necessary
				.anyRequest().authenticated().and()
				.exceptionHandling()
				.authenticationEntryPoint(exceptionHandler).and()
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(Arrays.asList("*")); // Consider specifying allowed origins
		config.setAllowedMethods(Arrays.asList("*")); // Specify allowed methods
		config.setAllowedHeaders(Arrays.asList("*"));
		config.setAllowCredentials(false);
		source.registerCorsConfiguration("/**", config);
		return source;
	}
}