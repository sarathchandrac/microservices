package com.spring.cs.accessories.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document
public class Accessory {
	@Id
	private String id;
	private String name;
	private String description;
	private String mobileType;
	private boolean isActive;

}
