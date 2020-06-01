package com.training.mobile.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MobileDto {
	private int id;
	private String name;
	private String status;
	private String lob;
	private String countryCode;
	private String publicationDate;
	private int price;

}
