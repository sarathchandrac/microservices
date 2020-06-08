package com.spring.cs.country.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.cs.common.dto.Response;
import com.spring.cs.country.model.Country;
import com.spring.cs.country.service.CountryService;

@RestController
@RequestMapping("country")
public class CountryController {

	@Autowired
	private CountryService countryService;

	@GetMapping("{country-code}")
	public Response<Country> getCountryByCode(@PathVariable("country-code") String countryCode) {
		Country country = countryService.getCountryByCode(countryCode);
		return Response.<Country>builder().response(country).build();
	}

}
