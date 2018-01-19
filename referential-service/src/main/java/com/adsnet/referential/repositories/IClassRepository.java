package com.adsnet.referential.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.adsnet.referential.entities.Classe;
import com.adsnet.referential.entities.Path;


@Repository
public interface IClassRepository extends JpaRepository<Classe, String>{
	
	/**
	 * Get the Class by its ID
	 * @param id
	 * @return
	 */
	Optional<Classe> findById(String id);
	
	/**
	 * This method get a path and return all the Classes available from this path
	 * @param path
	 * @return
	 */
	List<Classe> findByPath (Path path);
}
