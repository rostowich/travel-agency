package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.referential.entities.Bus;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;

/**
 * This interface exposes all the business operations available for the Bus entity
 * @author Rostow
 *
 */
@Component
public interface IBusService {

	/**
	 * This method saves the bus into the database
	 * @param bus
	 * @return
	 */
	public Bus save(Bus bus) throws ItemAlreadyExistException;
	
	/**
	 * This method saves the bus into the database
	 * @param bus
	 * @return
	 */
	public Bus update(Bus bus);
	
	/**
	 * This method saves the bus into the database
	 * @param bus
	 * @return
	 */
	public Optional<Bus> findBusById(Long id);
	
	/**
	 * This method delete the bus into the database
	 * @param bus
	 * @return
	 */
	public void delete(Long id);
	
	/**
	 * This method finds all the bus into the database
	 * @param label
	 * @return
	 */
	public List<Bus> findAll();
	
	
	/**
	 * This method finds all the bus using query by example
	 * @param label
	 * @return
	 */
	public Page<Bus> findAllByExample(Bus bus, Pageable pageable);
}
