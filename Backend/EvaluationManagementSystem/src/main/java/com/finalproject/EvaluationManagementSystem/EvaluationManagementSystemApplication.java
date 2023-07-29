package com.finalproject.EvaluationManagementSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class EvaluationManagementSystemApplication{
//	@Override
//	public void addCorsMappings(CorsRegistry registry) {
//		registry.addMapping("/**")
//				.allowedOrigins("http://127.0.0.1:5500") // Replace with your frontend origin
//				.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Allowed HTTP methods
//				.allowedHeaders("Content-Type","Authorization") // Allowed headers
//				.exposedHeaders("Authorization") // Headers exposed to the client
//				.allowCredentials(true) // Allow cookies and other credentials
//				.maxAge(3600L); // Max age of the preflight request in seconds
//	}

	public static void main(String[] args) {
		SpringApplication.run(EvaluationManagementSystemApplication.class, args);
	}

}
