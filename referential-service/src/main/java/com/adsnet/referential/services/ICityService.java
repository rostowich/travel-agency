package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.referential.entities.City;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;

/**
 * This interface exposes all the business operations available for the City entity
 * @author Rostow
 *
 */
@Component
public interface ICityService {

	/**
	 * This method saves the city into the database
	 * @param city
	 * @return
	 */
	public City save(City city) throws ItemAlreadyExistException;
	
	/**
	 * This method saves the city into the database
	 * @param city
	 * @return
	 */
	public Optional<City> findCityById(Long id);
	
	/**
	 * This method delete the city into the database
	 * @param city
	 * @return
	 */
	public void delete(Long id);
	
	/**
	 * This method finds all the cities into the database
	 * @param label
	 * @return
	 */
	public List<City> findAll();
	
	/**
	 * This method finds all the cities into the database
	 * @param label
	 * @return
	 */
	public Optional<City> findByLabel(String label);
	
	
	/**
	 * This method finds all the city using query by example
	 * @param label
	 * @return
	 */
	public Page<City> findAllByExample(City city, Pageable pageable);
}
