package com.adsnet.referential.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsnet.referential.entities.City;


/**
 * This class represents the Repository of the City entity.
 * It allows to access some repository methods to deal with the data source
 * @author Rostow
 *
 */
@Repository
public interface ICityRepository extends JpaRepository<City, Long>{

	/**
	 * Get the city by its ID
	 * @param id
	 * @return
	 */
	Optional<City> findById(Long id);
	
	/**
	 * Get the city by its label
	 * @param id
	 * @return
	 */
	Optional<City> findByLabel(String label);
	
	/**
	 * Get the city by its name
	 */
	List<City> findByLabelIgnoreCaseContaining(String label);
	
}
