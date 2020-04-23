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
	
	private static final Logger LOGGER=LoggerFactory.getLogger(OrmLearnApplication.class);
	
	private static CountryService countryService;
	
	public static void main(String[] args) {
		LOGGER.info("INSIDE MAIN");
		ApplicationContext applicationContext =SpringApplication.run(OrmLearnApplication.class, args);
		countryService=applicationContext.getBean(CountryService.class);
		getAllCountriesContaining("ou");
		getAllCountriesOrdered("ou");
		getAllCountriesByAlphabet('Z');
	}
	private static void getAllCountriesContaining(String name) {
		LOGGER.info("Started Genarating Countries by"+ name +"Given");
		List<Country> countryByName=null;
		try {
			countryByName = countryService.getCountryByName(name);
		} catch (CountryNotFoundException e) {
			
			e.printStackTrace();
		}
		for (Country country:countryByName) 
			System.out.println(country.getCode()+"\t"+country.getName());
			
		LOGGER.info("End");
	}
	private static void getAllCountriesOrdered(String name) {
		LOGGER.info("Started Genarating Countries Ordered by"+ name +"Given");
		List<Country> countryByName=null;
		try {
			countryByName = countryService.getCountryByNameOrdered(name);
		} catch (CountryNotFoundException e) {
			
			e.printStackTrace();
		}
		for (Country country:countryByName) 
			System.out.println(country.getCode()+"\t"+country.getName());
			
		LOGGER.info("End");
	}
	private static void getAllCountriesByAlphabet(char alphabet) {
		LOGGER.info("Started Genarating Countries Ordered by"+ alphabet +"Given");
		List<Country> countryByName=null;
		try {
			countryByName = countryService.getCountryByAlphabet(alphabet);
		} catch (CountryNotFoundException e) {
			
			e.printStackTrace();
		}
		for (Country country:countryByName) 
			System.out.println(country.getCode()+"\t"+country.getName());
			
		LOGGER.info("End");
	}
	}

