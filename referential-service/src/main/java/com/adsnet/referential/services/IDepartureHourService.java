package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.referential.entities.DepartureHour;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;

/**
 * This interface exposes all the business operations available for the DepartureHour entity
 * @author Rostow
 *
 */
@Component
public interface IDepartureHourService {

	/**
	 * This method saves the DepartureHour into the database
	 * @param DepartureHour
	 * @return
	 */
	public DepartureHour save(DepartureHour departureHour) throws ItemAlreadyExistException ;
	
	/**
	 * This method delete the DepartureHour into the database
	 * @param DepartureHour
	 * @return
	 */
	public void delete(Long id);
	
	/**
	 * This method find the DepartureHour by its ID
	 * @param id
	 * @return
	 */
	public Optional<DepartureHour> findById (Long id);
	
	/**
	 * The method return all the DepartureHour found into the database
	 * @return
	 */
	public List<DepartureHour> findAll();
	
	
	/**
	 * This method finds all the DepartureHour using query by example
	 * @param label
	 * @return
	 */
	public Page<DepartureHour> findAllByExample(DepartureHour departureHour, Pageable pageable);
}
