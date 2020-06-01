package com.spring.demo;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class HelloWorld {
	public String sayHello(String argument) {
		String message = "Welcome to Spring AOP !!";
		if(!StringUtils.isEmpty(argument)) {
			return message;
		} 
		throw new RuntimeException("Test Exception");
	}

	public String throwHello(String arg) {
		System.out.println( "Runtime Exception");
		throw new RuntimeException("Runtime Exception");
	}
	
	
	
}
