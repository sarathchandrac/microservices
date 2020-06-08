package com.spring.cs.mobile.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cs.common.dto.MobileDto;

import reactor.core.publisher.Mono;

@Service
public class MobileClientService {
	@Autowired
	private MobileServiceIntegration mobileService;

	public Mono<MobileDto> getMobileByid(int mobileId) {
		
		return mobileService.getMobileByid(mobileId);
	}

}
