package com.adsnet.referential.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsnet.referential.entities.DepartureHour;

/**
 * This class represents the Repository of the DepartureHour entity.
 * It allows to access some repository methods to deal with the data source
 * @author Rostow
 *
 */
@Repository
public interface IDepartureHourRepository extends JpaRepository<DepartureHour, Long>{

	/**
	 * Get the city by its ID
	 * @param id
	 * @return
	 */
	Optional<DepartureHour> findById(Long id);	
	
	/**
	 * Get the city by its label
	 * @param id
	 * @return
	 */
	Optional<DepartureHour> findByLabel(String label);
	
}
