package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adsnet.referential.entities.DepartureHour;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.repositories.IDepartureHourRepository;

@Service
public class DepartureHourService implements IDepartureHourService{
	
	@Autowired
	private IDepartureHourRepository departureHourRepository;

	@Override
	public DepartureHour save(DepartureHour departureHour) throws ItemAlreadyExistException {
		Optional<DepartureHour> dhExist=departureHourRepository.findByLabel(departureHour.getLabel());
		if(dhExist.isPresent())
			throw new ItemAlreadyExistException("Cette heure de depart existe dejà");
		return departureHourRepository.save(departureHour);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		departureHourRepository.delete(id);
	}

	@Override
	public Optional<DepartureHour> findById(Long id) {
		// TODO Auto-generated method stub
		return departureHourRepository.findById(id);
	}

	@Override
	public List<DepartureHour> findAll() {
		// TODO Auto-generated method stub
		return departureHourRepository.findAll();
	}

	@Override
	public Page<DepartureHour> findAllByExample(DepartureHour departureHour, Pageable pageable) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher=ExampleMatcher.matching()
				.withMatcher("id", match -> match.exact())
				.withMatcher("label", match ->match.contains().ignoreCase())
				.withIgnoreNullValues();
		
		Example<DepartureHour> example = Example.of(departureHour, matcher);
		
		return departureHourRepository.findAll(example, pageable);
	}
	
	

}
