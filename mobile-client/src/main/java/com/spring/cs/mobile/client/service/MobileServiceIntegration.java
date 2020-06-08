package com.spring.cs.mobile.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.spring.cs.common.dto.MobileDto;
import com.spring.cs.common.dto.Response;

import reactor.core.publisher.Mono;

@Service
public class MobileServiceIntegration {

	@Autowired
	private WebClient mobileServiceWebClient;

	public Mono<MobileDto> getMobileByid(int mobileId) {
		Mono<Response<MobileDto>> response = mobileServiceWebClient.get().uri("/{id}", mobileId)
				.accept(MediaType.APPLICATION_JSON).retrieve()
				.bodyToMono(new ParameterizedTypeReference<Response<MobileDto>>() {
				});
		return response.flatMap(responseObj -> Mono.just(responseObj.getResponse()));
	}
}
