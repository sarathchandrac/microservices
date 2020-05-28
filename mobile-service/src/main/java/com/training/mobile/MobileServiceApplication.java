package com.training.mobile;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MobileServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobileServiceApplication.class, args);
		System.out.println("Mobile Service");
	}
	
	@Bean
	public CommandLineRunner start() {
		
			System.out.println("Fetch Mobile Info Start >>>>>");
			return System.out::println;
			
	
		
	}

}
