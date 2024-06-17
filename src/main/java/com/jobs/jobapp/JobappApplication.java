package com.jobs.jobapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Jobs Microservice REST API Documentation",
				description = "Microservice REST API Documentation for job postings, categories, and user management.",
				version = "v1"
		)
)
public class JobappApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobappApplication.class, args);
	}

}
