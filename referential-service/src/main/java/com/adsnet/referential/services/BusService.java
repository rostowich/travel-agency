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

import com.adsnet.referential.entities.Bus;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.repositories.IBusRepository;


@Service
public class BusService implements IBusService {

	private static final Logger logger = LoggerFactory.getLogger(BusService.class);

	@Autowired
	IBusRepository busRepository;
	
	
	@Override
	public Bus save(Bus bus) throws ItemAlreadyExistException {
		//We need to check if this bus already exists
		Optional<Bus> busExist=busRepository.findByImmatriculation(bus.getImmatriculation());
		if(busExist.isPresent())
			throw new ItemAlreadyExistException("Ce bus existe dejà");
		
		return busRepository.save(bus);
	}
	
	@Override
	public Bus update(Bus bus) {
		// TODO Auto-generated method stub
		return busRepository.save(bus);
	}

	@Override
	public void delete(Long id) {
		busRepository.delete(id);
	}

	@Override
	public Page<Bus> findAllByExample(Bus bus, Pageable pageable) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher=ExampleMatcher.matching()
				.withMatcher("id", match -> match.exact())
				.withMatcher("immatriculation", match -> match.contains().ignoreCase())
				.withMatcher("type", match -> match.exact())
				.withMatcher("number_of_places", match -> match.exact())
				.withMatcher("mark", match -> match.exact())
				.withIgnoreNullValues();
		
		Example<Bus> example = Example.of(bus, matcher);
		return busRepository.findAll(example, pageable);
	}

	@Override
	public List<Bus> findAll() {
		// TODO Auto-generated method stub
		return busRepository.findAll();
	}

	@Override
	public Optional<Bus> findBusById(Long id) {
		// TODO Auto-generated method stub
		return busRepository.findById(id);
	}

	
}
