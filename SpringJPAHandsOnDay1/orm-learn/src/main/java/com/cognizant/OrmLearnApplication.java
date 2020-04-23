package com.cognizant;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.service.CountryService;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@SpringBootApplication
public class OrmLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrmLearnApplication.class);

	private static CountryService countryService;

	public static void main(String[] args) {
		SpringApplication.run(OrmLearnApplication.class, args);
		LOGGER.info("Inside Main");
		ApplicationContext applicationContext = SpringApplication.run(OrmLearnApplication.class, args);
		countryService = applicationContext.getBean(CountryService.class);

		testGetAllCountries();
		getCountriesByCodeTest();
		testAddCountry();
		Country country =new Country("XX", "YCountry");
		testUpdateCountry(country);
		testDeleteCountry(country);

	}

	private static void testGetAllCountries() {
		LOGGER.info("Start");
		List<Country> countries = countryService.getAllCountries();
		LOGGER.debug("countries={}", countries);
		LOGGER.info("End");

	}

	private static void getCountriesByCodeTest() {
		LOGGER.info("Started Getting Countries By Code");
		Country country = null;
		try {
			country = countryService.findCountryByCode("IN");
		} catch (CountryNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		LOGGER.debug("Country:{}", country);
		LOGGER.info("END");
	}

	private static void testAddCountry() {
		
		Country country = new Country("XX", "XCountry");
		LOGGER.info("Started Adding");
		countryService.addCountry(country);
		try {
			countryService.findCountryByCode("XX");
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.debug("Country Added", country);
		LOGGER.info("END");

	}

	private static void testUpdateCountry(Country country) {
		LOGGER.info("Started Updating");
		try {
			countryService.updateCountry(country.getCode(), country.getName());
		} catch (CountryNotFoundException e) {

			e.printStackTrace();
		}
		LOGGER.debug("Country Updated", country);
		LOGGER.info("END");
	}
	
	private static void testDeleteCountry(Country country) {
		LOGGER.info("Started Deleting");
		try {
			countryService.deleteCountry(country);
		} catch (CountryNotFoundException e) {
			e.printStackTrace();
		}
		LOGGER.debug("Country Deleted", country);
		LOGGER.info("END");

	}
	
}
