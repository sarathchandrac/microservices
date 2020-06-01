package com.training.mobile.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class SaveMobileRequest {
	@NotBlank(message="{NotBlank.SaveMobileRequest.name}")
	private String name;
	@Min(value=1, message="{Min.SaveMobileRequest.price}" )
	@Max(value=2000000, message="{Max.SaveMobileRequest.price}")
	private int price;
	@NotBlank(message="{NotBlank.SaveMobileRequest.status}")
	private String status;
	@NotBlank(message="{NotBlank.SaveMobileRequest.lob}")
	private String lob;
	@NotBlank(message="{NotBlank.SaveMobileRequest.countryCode}")
	private String countryCode;

}
