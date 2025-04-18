package com.example.Lab.Aspect3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.Lab.Aspect3.repo") // Add this line to scan repositories
public class LabAspect3Application {
	public static void main(String[] args) {
		SpringApplication.run(LabAspect3Application.class, args);
	}
}
