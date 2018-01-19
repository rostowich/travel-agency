package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adsnet.referential.entities.Path;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.repositories.IPathRepository;

@Service
public class PathService implements IPathService{

	private static final Logger logger = LoggerFactory.getLogger(PathService.class);
	
	@Autowired
	private IPathRepository pathRepository;
	
	
	@Override
	public Path save(Path path) throws ItemAlreadyExistException{
		//We need to check if this path already exists 
		System.out.println("path === "+path.toString());
		Optional<Path> pathExist=pathRepository.findById(path.getId());
		if(pathExist.isPresent())
			throw new ItemAlreadyExistException("Ce trajet existe déjà dans le système");
		return pathRepository.save(path);
	}

	
	@Override
	public List<Path> findAll() {
		// TODO Auto-generated method stub
		return pathRepository.findAll();
	}


	@Override
	public void delete(String pathId) {
		pathRepository.delete(pathId);
	}


	
	@Override
	public Page<Path> findAllByExample(Path path, Pageable pageable) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher=ExampleMatcher.matching()
				.withMatcher("id", match -> match.exact())
				.withMatcher("departure.id", match -> match.exact())
				.withMatcher("destination.id", match -> match.exact())
				.withIgnoreNullValues();
		
		Example<Path> example = Example.of(path, matcher);
		return pathRepository.findAll(example, pageable);
	}


	@Override
	public Optional<Path> findPathById(String id) {
		// TODO Auto-generated method stub
		return pathRepository.findById(id);
	}

}	



	