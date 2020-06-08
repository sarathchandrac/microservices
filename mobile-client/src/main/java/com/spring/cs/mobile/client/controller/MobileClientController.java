package com.spring.cs.mobile.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cs.common.dto.MobileDto;
import com.spring.cs.mobile.client.service.MobileClientService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("mobile-client")
public class MobileClientController {
	@Autowired
	private MobileClientService service;
@GetMapping("{mobile-id}")
public Mono<MobileDto> getMobileFromMobileService(@PathVariable("mobile-id") int mobileId){
	return service.getMobileByid(mobileId);
	
}
}
