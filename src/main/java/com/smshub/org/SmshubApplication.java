package com.smshub.org;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
@EnableJpaAuditing
public class SmshubApplication {
	public static void main(String[] args) { 
		SpringApplication.run(SmshubApplication.class, args);
	}
}
