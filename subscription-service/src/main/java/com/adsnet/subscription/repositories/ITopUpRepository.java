package com.adsnet.subscription.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.adsnet.subscription.entities.TopUp;


@Repository
public interface ITopUpRepository extends JpaRepository<TopUp, Long>, JpaSpecificationExecutor<TopUp>{
	
	/**
	 * Get the topup by its ID
	 * @param id
	 * @return
	 */
	Optional<TopUp> findById(Long id);
		
}
