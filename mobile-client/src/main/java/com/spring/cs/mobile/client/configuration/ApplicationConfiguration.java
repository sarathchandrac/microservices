package com.spring.cs.mobile.client.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ApplicationConfiguration {

	@Value("${mobile-service-base-url}")
	private String mobileServiceBaseUrl;
	
	@Bean
	public WebClient mobileServiceWebClient() {
		return WebClient.create(mobileServiceBaseUrl);
	}
}
