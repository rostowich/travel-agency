package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.referential.entities.CashRegister;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;

/**
 * This interface exposes all the business operations available for the cash register entity
 * @author Rostow
 *
 */
@Component
public interface ICashRegisterService {

	/**
	 * This method saves the cash register into the database
	 * @param cash register
	 * @return
	 */
	public CashRegister save(CashRegister cashRegister) throws ItemAlreadyExistException;
	
	/**
	 * This method delete the cash register into the database
	 * @param id
	 * @return
	 */
	public void delete(Long id);
	
	/**
	 * This method find the cash register by its ID
	 * @param id
	 * @return
	 */
	public Optional<CashRegister> findById (Long id);
	
	/**
	 * The method return all the cash registers found into the database
	 * @return
	 */
	public List<CashRegister> findAll();
	
	
	/**
	 * This method finds all the cash register using query by example
	 * @param label
	 * @return
	 */
	public Page<CashRegister> findAllByExample(CashRegister cashRegister, Pageable pageable);
}
