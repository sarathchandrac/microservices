package com.training.mobile.dto;

import java.time.LocalDate;

import com.training.mobile.model.LOB;
import com.training.mobile.model.Status;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MobileDto {
	private int id;
	private String name;
	private String status;
	private String lob;
	private String countryCode;
	private String publicationDate;
	private int price;

}
