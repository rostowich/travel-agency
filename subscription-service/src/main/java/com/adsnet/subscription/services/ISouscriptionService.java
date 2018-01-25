package com.adsnet.subscription.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.subscription.entities.Souscription;
import com.adsnet.subscription.exceptions.ItemAlreadyExistException;


@Component
public interface ISouscriptionService {

	/**
	 * This method saves an souscription into the database
	 * @param city
	 * @return
	 */
	public Souscription save(Souscription souscription) throws ItemAlreadyExistException;
	
	/**
	 * This method saves an souscription into the database
	 * @param city
	 * @return
	 */
	public Souscription update(Souscription souscription);
	
	/**
	 * This method deletes an souscription into the database
	 * @param souscriptionId
	 * @return
	 */
	public void delete(Long id);
	
	
	/**
	 * The method return all the souscriptions found into the database
	 * @return
	 */
	public List<Souscription> findAll();
	
	
	/**
	 * This method finds all the souscriptions using query by example
	 * @param label
	 * @return
	 */
	public Page<Souscription> findAllByExample(Souscription souscription, Pageable pageable);
	
	/**
	 * This method finds the souscription into the database
	 * @param souscription
	 * @return
	 */
	public Optional<Souscription> findSouscriptionById(Long id);
	
}
