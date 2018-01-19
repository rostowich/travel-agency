package com.adsnet.referential.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.adsnet.referential.entities.Driver;


/**
 * This class represents the Repository of the Driver entity.
 * It allows to access some repository methods to deal with the data source
 * @author Rostow
 *
 */
@Repository
public interface IDriverRepository extends JpaRepository<Driver, Long>, JpaSpecificationExecutor<Driver>{

	/**
	 * Get the Driver by its ID
	 * @param id
	 * @return
	 */
	Optional<Driver> findById(Long id);
	
	/**
	 * Get the driver by its driver licence number
	 * @param id
	 * @return
	 */
	Optional<Driver> findByDriverLicenceNumber(String driverLicenceNumber);
	
	
}
