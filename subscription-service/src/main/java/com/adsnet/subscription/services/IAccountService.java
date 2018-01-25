package com.adsnet.subscription.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.subscription.entities.Account;
import com.adsnet.subscription.exceptions.ItemAlreadyExistException;


@Component
public interface IAccountService {

	/**
	 * This method saves an account into the database
	 * @param city
	 * @return
	 */
	public Account save(Account account) throws ItemAlreadyExistException;
	
	/**
	 * This method saves an account into the database
	 * @param city
	 * @return
	 */
	public Account update(Account account);
	
	/**
	 * This method deletes an account into the database
	 * @param accountId
	 * @return
	 */
	public void delete(Long id);
	
	
	/**
	 * The method return all the accounts found into the database
	 * @return
	 */
	public List<Account> findAll();
	
	
	/**
	 * This method finds all the accounts using query by example
	 * @param label
	 * @return
	 */
	public Page<Account> findAllByExample(Account account, Pageable pageable);
	
	/**
	 * This method saves the account into the database
	 * @param account
	 * @return
	 */
	public Optional<Account> findAccountById(Long id);
	
}
