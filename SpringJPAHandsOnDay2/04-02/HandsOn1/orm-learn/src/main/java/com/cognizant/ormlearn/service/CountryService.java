package com.cognizant.ormlearn.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.ormlearn.model.Country;
import com.cognizant.ormlearn.repository.CountryRepository;
import com.cognizant.ormlearn.service.exception.CountryNotFoundException;

@Service
public class CountryService {
	@Autowired
	private CountryRepository countryRepository;
	
	@Transactional
	public List<Country> getCountryByName(String name) throws CountryNotFoundException {

		List<Country> findByNameContaining = countryRepository.findByNameContaining(name);
		if (findByNameContaining.isEmpty()) {
			throw new CountryNotFoundException("Country containg name is not found");
		}
		return findByNameContaining;

	}
	@Transactional
	public List<Country> getCountryByNameOrdered(String name) throws CountryNotFoundException{
		List<Country> findByNameOrdered=countryRepository.findByNameContainingOrderByName(name);
		if(findByNameOrdered.isEmpty()) {
			throw new CountryNotFoundException("Country containg name is not found");
		}
		return findByNameOrdered;
	}
	@Transactional
	public List<Country> getCountryByAlphabet(char alphabet) throws CountryNotFoundException{
		List<Country> findByNameStartsWith = countryRepository.findByNameStartsWith(alphabet);
		if(findByNameStartsWith.isEmpty()) {
			throw new CountryNotFoundException("No Countries with Alphabets found");
			
		}
		return findByNameStartsWith;
	}
}