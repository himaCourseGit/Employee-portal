package com.acintyo.config;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import jakarta.servlet.http.HttpServletRequest;

//@Configuration
public class Config 
{
	private CorsConfigurationSource corsConfigurationSource() {
		return new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration ccfg = new CorsConfiguration();
				ccfg.setAllowedOrigins(Arrays.asList("http://localhost:3000", "16.171.145.147:6060"));
				ccfg.setAllowedMethods(Collections.singletonList("*"));
				ccfg.setAllowCredentials(true);
				ccfg.setAllowedHeaders(Collections.singletonList("*"));
				ccfg.setExposedHeaders(Arrays.asList("Authorization"));
				ccfg.setMaxAge(3600L);
				return ccfg;
			}
		};

	}

	@Bean
	CorsConfigurationSource corsConfigurationSource1() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
		configuration.setAllowedOrigins(Collections.singletonList("16.171.145.147:6060"));
		configuration.setAllowedHeaders(List.of("*"));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "OPTIONS", "PUT", "DELETE"));
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
