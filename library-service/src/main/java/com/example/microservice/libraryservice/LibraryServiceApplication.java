package com.example.microservice.libraryservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
@EnableTransactionManagement
public class LibraryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryServiceApplication.class, args);
	}

	@Autowired
	private Environment environment;

	public void getActiveProfiles() {
		for (String profileName : environment.getActiveProfiles()) {
			System.out.println("Currently active profile - " + profileName);
		}
	}

	@Bean
	public String test() {
		getActiveProfiles();
		return "test";
	}

}
