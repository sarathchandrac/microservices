package com.training.mobile.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloWorldController {
	
	@GetMapping("/greeting")
	public String greeting(){
		return "Welcome";
	}
	
	@GetMapping("/getmobiles")
	public String getMobiles(){
		return "Welcome";
	}
}
