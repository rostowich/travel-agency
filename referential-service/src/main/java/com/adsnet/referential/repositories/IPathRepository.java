package com.adsnet.referential.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsnet.referential.entities.Path;

@Repository
public interface IPathRepository extends JpaRepository<Path, String>{
	
	/**
	 * Get the path by its ID
	 * @param id
	 * @return
	 */
	Optional<Path> findById(String id);
	
	
}
