package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.referential.entities.Pricing;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;


@Component
public interface IPrincingService {

	/**
	 * This method saves a pricing into the database
	 * @param pricing
	 * @return
	 */
	public Pricing save(Pricing pricing) throws ItemAlreadyExistException;
	
	/**
	 * This method updates a pricing into the database
	 * @param pricing
	 * @return
	 */
	public Pricing update(Pricing pricing);
	
	/**
	 * This method deletes a pricing into the database
	 * @param pricingId
	 * @return
	 */
	public void delete(String pricingId);
	
	
	/**
	 * The method return all the pricing found into the database
	 * @return
	 */
	public List<Pricing> findAll();
	
	/**
	 * The method return all the pricing by example
	 * @return
	 */
	public Page<Pricing> findAllByExample(Pricing pricing, Pageable pageable);
	
	
	/**
	 * This method saves the path into the database
	 * @param path
	 * @return
	 */
	public Optional<Pricing> findPricingById(String id);
}
