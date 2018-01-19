package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adsnet.referential.entities.Pricing;
import com.adsnet.referential.exceptions.ItemAlreadyExistException;
import com.adsnet.referential.repositories.IPricingRepository;

@Service
public class PricingService implements IPrincingService{
	
	@Autowired
	private IPricingRepository pricingRepository;

	@Override
	public Pricing save(Pricing pricing) throws ItemAlreadyExistException{
		//We need to check if the pricing already exists
		Optional<Pricing> pricingExist=pricingRepository.findById(pricing.getId());
		if(pricingExist.isPresent())
			throw new ItemAlreadyExistException("Cette tarification existe deja");
		return pricingRepository.save(pricing);
	}

	@Override
	public void delete(String pricingId) {
		// TODO Auto-generated method stub
		pricingRepository.delete(pricingId);
	}

	@Override
	public List<Pricing> findAll() {
		// TODO Auto-generated method stub
		return pricingRepository.findAll();
	}
	
	@Override
	public Page<Pricing> findAllByExample(Pricing pricing, Pageable pageable) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher=ExampleMatcher.matching()
				.withMatcher("id", match -> match.exact())
				.withMatcher("amount", match ->match.exact())
				.withMatcher("travelType", match -> match.exact())
				.withMatcher("classe.id", match ->match.exact())
				.withIgnoreNullValues();
		
		Example<Pricing> example = Example.of(pricing, matcher);
		
		return pricingRepository.findAll(example, pageable);
	}

	@Override
	public Pricing update(Pricing pricing) {
		// TODO Auto-generated method stub
		return pricingRepository.save(pricing);
	}

	@Override
	public Optional<Pricing> findPricingById(String id) {
		// TODO Auto-generated method stub
		return pricingRepository.findById(id);
	}
}
