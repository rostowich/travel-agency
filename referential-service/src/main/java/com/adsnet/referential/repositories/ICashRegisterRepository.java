package com.adsnet.referential.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsnet.referential.entities.CashRegister;

/**
 * This class represents the Repository of the Cash register entity.
 * It allows to access some repository methods to deal with the data source
 * @author Rostow
 *
 */
@Repository
public interface ICashRegisterRepository extends JpaRepository<CashRegister, Long>{

	/**
	 * Get the cash register by its ID
	 * @param id
	 * @return
	 */
	Optional<CashRegister> findById(Long id);
	
	/**
	 * Get the city by its label
	 * @param id
	 * @return
	 */
	Optional<CashRegister> findByLabel(String label);
	
	/**
	 * Get the city by its name
	 */
	List<CashRegister> findByLabelIgnoreCaseContaining(String label);
	
	
	
}
