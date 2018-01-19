package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.referential.entities.Classe;


@Component
public interface IClassService {

	/**
	 * This method saves a class into the database
	 * @param city
	 * @return
	 */
	public Classe save(Classe classe);
	
	/**
	 * This method deletes a class into the database
	 * @param id
	 * @return
	 */
	public void delete(String id);
	
	/**
	 * This method finds the class by its ID
	 * @param id
	 * @return
	 */
	public Optional<Classe> findById (String id);
	
	
	/**
	 * The method return all the classes found into the database
	 * @return
	 */
	public List<Classe> findAll();
	
	
	/**
	 * Get all the classes from an example class
	 * @param city
	 * @return
	 */
	public Page<Classe> findAllByExample(Classe classe, Pageable pageable);
}
