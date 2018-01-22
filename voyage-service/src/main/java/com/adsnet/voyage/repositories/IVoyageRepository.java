package com.adsnet.voyage.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.adsnet.voyage.entities.Voyage;


@Repository
public interface IVoyageRepository extends JpaRepository<Voyage, String>, JpaSpecificationExecutor<Voyage>{
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Voyage> findById(String id);
	
}
