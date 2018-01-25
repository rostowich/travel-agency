package com.adsnet.subscription.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsnet.subscription.entities.Account;

@Repository
public interface IAccountRepository extends JpaRepository<Account, Long>{
	
	/**
	 * Get the account by its ID
	 * @param id
	 * @return
	 */
	Optional<Account> findById(Long id);
	
	/**
	 * Get the account by its label
	 * @param label
	 * @return
	 */
	Optional<Account> findByLabel(String label);
	
	/**
	 * Get the account by its number
	 * @param number
	 * @return
	 */
	Optional<Account> findByNumber(String number);	
	
}
