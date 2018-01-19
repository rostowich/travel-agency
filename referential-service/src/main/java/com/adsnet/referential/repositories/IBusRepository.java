package com.adsnet.referential.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsnet.referential.entities.Bus;


/**
 * This class represents the Repository of the City entity.
 * It allows to access some repository methods to deal with the data source
 * @author Rostow
 *
 */
@Repository
public interface IBusRepository extends JpaRepository<Bus, Long>{

	/**
	 * Get the Bus by its ID
	 * @param id
	 * @return
	 */
	Optional<Bus> findById(Long id);
	
	/**
	 * Get the city by its label
	 * @param id
	 * @return
	 */
	Optional<Bus> findByImmatriculation(String immatriculation);
	
	
}
