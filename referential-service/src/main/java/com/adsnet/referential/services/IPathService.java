package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.adsnet.referential.entities.Path;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;

@Component
public interface IPathService {

	/**
	 * This method saves a path into the database
	 * @param city
	 * @return
	 */
	public Path save(Path path) throws ItemAlreadyExistException;
	
	/**
	 * This method deletes a path into the database
	 * @param pathId
	 * @return
	 */
	public void delete(String pathId);
	
	
	/**
	 * The method return all the paths found into the database
	 * @return
	 */
	public List<Path> findAll();
	
	
	/**
	 * This method finds all the paths using query by example
	 * @param label
	 * @return
	 */
	public Page<Path> findAllByExample(Path city, Pageable pageable);
	
	/**
	 * This method saves the path into the database
	 * @param path
	 * @return
	 */
	public Optional<Path> findPathById(String id);
	
}
