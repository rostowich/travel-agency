package com.adsnet.subscription.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.subscription.entities.TopUp;
import com.adsnet.subscription.exceptions.AccountNotFoundException;
import com.adsnet.subscription.exceptions.InsuffisantAmountIntoAccountException;


@Component
public interface ITopUpService {

	/**
	 * This method saves a topUp into the database
	 * @param topUp
	 * @return
	 * @throws ItemAlreadyExistException
	 */
	public TopUp save(TopUp topUp) throws InsuffisantAmountIntoAccountException, AccountNotFoundException;
	
	
	/**
	 * The method return all the topUps found into the database
	 * @return
	 */
	public List<TopUp> findAll();
	
	
	/**
	 * This method finds all the topUps using query by example
	 * @param label
	 * @return
	 */
	public Page<TopUp> findAllByExample(TopUp topUp, Pageable pageable);
	
	/**
	 * This method saves the topUp into the database
	 * @param topUp
	 * @return
	 */
	public Optional<TopUp> findTopUpById(Long id);
	
}
