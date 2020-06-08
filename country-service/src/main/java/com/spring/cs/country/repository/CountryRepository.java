package com.spring.cs.country.repository;

import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import com.spring.cs.country.model.Country;
import org.springframework.jdbc.core.RowMapper;

@Repository
public class CountryRepository {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Country getCountryByCode(String countryCode) {
		String query = "select code , name, continent, region, population from country where code = ? ";
		
		return jdbcTemplate.queryForObject(query, new CountryRowMapper(), countryCode);
	}
	
	
	public Country getCountryByReigon(String region) {
		String query = "select code , name, continent, region, population from country where region = ? ";
		
		return jdbcTemplate.queryForObject(query, new CountryRowMapper(), region);
	}
	
	public List<Country> getAllCountries() {
		String query = "select code , name, continent, region, population from country";
		return jdbcTemplate.query(query,  new CountryRowMapper());
	}

	
	static  class CountryRowMapper implements RowMapper<Country>{

		@Override
		public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Country
					.builder()
					.code(rs.getString("code"))
					.name("name")
					.description("name")
					.continent(rs.getString("continent"))
					.region(rs.getString("region"))
					.population(rs.getLong("population")).build();
		}
		
	}
	public Country updateMobile(@Valid Country country) {
		String query = "select * from country set name = ? , continent = ? , region = ? , population= ? where code = ?";
		
		 int updateCount = jdbcTemplate.update(query, new Object[] {
										 country.getName(),
										 country.getContinent(),
										 country.getRegion(),
										 country.getPopulation(),
										 country.getCode()
								    });
		 if(updateCount > 0)
		  return country;
		 
		 throw new RuntimeException("Updated Failed. Please try after sometime.");
		 
	}


	public void deleteCountry(String countryCode) {
		String query = "delete from country where code = ?";
		 int updateCount = jdbcTemplate.update(query,countryCode);
		 if(updateCount <= 0)
			 throw new RuntimeException("Delete Failed. Please try after sometime."); 
	}


	
}
