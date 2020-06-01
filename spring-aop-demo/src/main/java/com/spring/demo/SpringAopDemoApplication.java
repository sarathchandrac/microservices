package com.spring.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAopDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringAopDemoApplication.class, args);
	}
	@Bean
	public CommandLineRunner start(HelloWorld hw) {
		return args -> {
			try {
				System.out.println(hw.sayHello("welcome"));
				
			}
			catch(Exception e) {
				System.err.println(e.getMessage());
			}
			
			
			//System.out.println(hw.throwHello("welcome"));
		};
		
	}

}
