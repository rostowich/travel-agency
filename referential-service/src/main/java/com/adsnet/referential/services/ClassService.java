package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adsnet.referential.entities.Classe;
import com.adsnet.referential.repositories.IClassRepository;


@Service
public class ClassService implements IClassService{

	@Autowired
	private IClassRepository classRepository;
	
	@Override
	public Classe save(Classe classe) {
		// TODO Auto-generated method stub
		return classRepository.save(classe);
	}

	@Override
	public Optional<Classe> findById(String id) {
		// TODO Auto-generated method stub
		return classRepository.findById(id);
	}

	@Override
	public List<Classe> findAll() {
		// TODO Auto-generated method stub
		return classRepository.findAll();
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		classRepository.delete(id);
	}

	@Override
	public Page<Classe> findAllByExample(Classe classe, Pageable pageable) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher=ExampleMatcher.matching()
				.withMatcher("id", match -> match.exact())
				.withMatcher("label", match ->match.contains().ignoreCase())
				.withMatcher("path.id", match -> match.exact())
				.withIgnoreNullValues();
		
		Example<Classe> example = Example.of(classe, matcher);
		return classRepository.findAll(example, pageable);
	}

}
