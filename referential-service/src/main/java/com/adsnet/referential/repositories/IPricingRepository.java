package com.adsnet.referential.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsnet.referential.entities.Pricing;

@Repository
public interface IPricingRepository extends JpaRepository<Pricing, String>{
	
	
	/**
	 * Get the path by its ID
	 * @param id
	 * @return
	 */
	Optional<Pricing> findById(String id);

}
