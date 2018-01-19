package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.referential.entities.Driver;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;

/**
 * This interface exposes all the business operations available for the Driver entity
 * @author Rostow
 *
 */
@Component
public interface IDriverService {

	/**
	 * This method saves the driver into the database
	 * @param driver
	 * @return
	 */
	public Driver save(Driver driver) throws ItemAlreadyExistException;
	
	/**
	 * This method saves the driver into the database
	 * @param driver
	 * @return
	 */
	public Driver update(Driver driver);
	
	/**
	 * This method saves the driver into the database
	 * @param driver
	 * @return
	 */
	public Optional<Driver> findDriverById(Long id);
	
	/**
	 * This method delete the driver into the database
	 * @param driver
	 * @return
	 */
	public void delete(Long id);
	
	/**
	 * This method finds all the driver into the database
	 * @param label
	 * @return
	 */
	public List<Driver> findAll();
	
	
	/**
	 * This method finds all the driver using query by example
	 * @param label
	 * @return
	 */
	public Page<Driver> findAllByExample(Driver driver, Pageable pageable);
}
