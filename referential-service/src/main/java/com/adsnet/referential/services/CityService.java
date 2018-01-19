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

import com.adsnet.referential.entities.City;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.repositories.ICityRepository;


@Service
public class CityService implements ICityService {

	private static final Logger logger = LoggerFactory.getLogger(CityService.class);

	@Autowired
	ICityRepository cityRepository;
	
	/*
	 * (non-Javadoc)
	 * @see com.adsnet.touristique.services.ICityService#save(com.adsnet.touristique.entities.City)
	 */
	@Override
	public City save(City city) throws ItemAlreadyExistException {
		//We need to check if this city already exists
		Optional<City> cityExist=cityRepository.findByLabel(city.getLabel());
		if(cityExist.isPresent())
			throw new ItemAlreadyExistException("Cette ville existe dejà");
		
		return cityRepository.save(city);
	}

	@Override
	public void delete(Long id) {
		cityRepository.delete(id);
	}

	@Override
	public Page<City> findAllByExample(City city, Pageable pageable) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher=ExampleMatcher.matching()
				.withMatcher("id", match -> match.exact())
				.withMatcher("label", match ->match.contains().ignoreCase())
				.withIgnoreNullValues();
		
		Example<City> example = Example.of(city, matcher);
		return cityRepository.findAll(example, pageable);
	}

	@Override
	public List<City> findAll() {
		// TODO Auto-generated method stub
		return cityRepository.findAll();
	}

	@Override
	public Optional<City> findByLabel(String label) {
		// TODO Auto-generated method stub
		return cityRepository.findByLabel(label);
	}

	@Override
	public Optional<City> findCityById(Long id) {
		// TODO Auto-generated method stub
		return cityRepository.findById(id);
	}
}
