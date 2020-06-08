package com.spring.cs.country.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.cs.country.model.Country;
import com.spring.cs.country.repository.CountryRepository;
@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;
	

	public List<Country> getAllCountries() {
		return countryRepository.getAllCountries();
	}
	
	public Country getCountryByCode(String countryCode) {
		return countryRepository.getCountryByCode(countryCode);
	}

	public Country getCountryByRegion(String region) {
		return countryRepository.getCountryByReigon(region);
	}

	public Country updateMobile(@Valid Country country) {
		return countryRepository.updateMobile(country);
	}

	public void deleteCountry(String countryCode) {
		countryRepository.deleteCountry(countryCode);
	}



}
