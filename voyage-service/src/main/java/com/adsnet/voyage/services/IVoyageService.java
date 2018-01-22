package com.adsnet.voyage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.voyage.entities.Voyage;
import com.adsnet.voyage.exceptions.ItemAlreadyExistException;


/**
 * This interface exposes all the business operations available for the Voyage entity
 * @author Rostow
 *
 */
@Component
public interface IVoyageService {

	/**
	 * This method saves the voyage into the database
	 * @param voyage
	 * @return
	 */
	public Voyage save(Voyage voyage) throws ItemAlreadyExistException;
	
	/**
	 * This method saves the voyage into the database
	 * @param voyage
	 * @return
	 */
	public Voyage update(Voyage voyage);
	
	/**
	 * This method saves the voyage into the database
	 * @param voyage
	 * @return
	 */
	public Optional<Voyage> findVoyageById(String id);
	
	/**
	 * This method delete the voyage into the database
	 * @param voyage
	 * @return
	 */
	public void delete(String id);
	
	/**
	 * This method finds all the voyage into the database
	 * @param label
	 * @return
	 */
	public List<Voyage> findAll();
	
	
	/**
	 * This method finds all the voyage using query by example
	 * @param label
	 * @return
	 */
	public Page<Voyage> findAllByExample(Voyage voyage, Pageable pageable);
}
