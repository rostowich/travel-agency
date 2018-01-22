package com.adsnet.voyage.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.adsnet.voyage.entities.Voyage;
import com.adsnet.voyage.exceptions.ItemAlreadyExistException;
import com.adsnet.voyage.repositories.IVoyageRepository;
import com.adsnet.voyage.repositories.VoyageSpecification;


@Service
public class VoyageService implements IVoyageService{
	
	@Autowired
	private IVoyageRepository voyageRepository;

	@Override
	public Voyage save(Voyage voyage) throws ItemAlreadyExistException {
		// TODO Auto-generated method stub
		voyage=initializeVoyage(voyage);
		Optional<Voyage> voyageSearch=voyageRepository.findById(voyage.getId());
		if(voyageSearch.isPresent())
			throw new ItemAlreadyExistException("Ce voyage existe déjà dans le système");
		
		return voyageRepository.save(voyage);
	}

	@Override
	public Voyage update(Voyage voyage) {
		// TODO Auto-generated method stub
		return voyageRepository.save(voyage);
	}

	@Override
	public Optional<Voyage> findVoyageById(String id) {
		// TODO Auto-generated method stub
		return voyageRepository.findById(id);
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		voyageRepository.delete(id);
	}

	@Override
	public List<Voyage> findAll() {
		// TODO Auto-generated method stub
		return voyageRepository.findAll();
	}

	@Override
	public Page<Voyage> findAllByExample(Voyage voyage, Pageable pageable) {
		// TODO Auto-generated method stub
		Specification<Voyage> specification=new VoyageSpecification(voyage);
		return voyageRepository.findAll(specification, pageable);
	}
	
	/**
	 * This method is used to set attributes of the Voyage entity before saving it
	 * @param voyage
	 * @return
	 */
	private static Voyage initializeVoyage(Voyage voyage){
		
		voyage.setId(voyage.getDepartureDate()+"_"+voyage.getDepartureHourId()+"_"+voyage.getPathId());
		voyage.setArchived(new Boolean(false));
		voyage.setNumberOfSalePlace(0);
		
		return voyage;
	}

}
