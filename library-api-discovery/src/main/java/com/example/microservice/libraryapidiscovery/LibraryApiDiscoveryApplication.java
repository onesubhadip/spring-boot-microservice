package com.example.microservice.libraryapidiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class LibraryApiDiscoveryApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryApiDiscoveryApplication.class, args);
	}

}
