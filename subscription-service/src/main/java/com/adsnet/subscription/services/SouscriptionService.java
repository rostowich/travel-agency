package com.adsnet.subscription.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.adsnet.subscription.entities.Souscription;
import com.adsnet.subscription.exceptions.ItemAlreadyExistException;
import com.adsnet.subscription.repositories.ISouscriptionRepository;
import com.adsnet.subscription.utils.UtilityFactory;


@Service
public class SouscriptionService implements ISouscriptionService{
	
	@Autowired
	private ISouscriptionRepository souscriptionRepository;
	
	@Autowired
	private UtilityFactory utilityFactory;

	@Override
	public Souscription save(Souscription souscription) throws ItemAlreadyExistException {
		// TODO Auto-generated method stub
		Optional<Souscription> souscriptionExist=souscriptionRepository.findByLoginOrEmailOrCompleteNameAllIgnoreCase(souscription.getLogin(),
			                                                            souscription.getEmail(), souscription.getCompleteName());
		if(souscriptionExist.isPresent())
			throw new ItemAlreadyExistException("Cet abonne existe déjà, veuillez verifier le login, l'email ou le nom");
	
		
		initializeSouscriptionForCreation(souscription);
		return souscriptionRepository.save(souscription);
	}

	@Override
	public Souscription update(Souscription souscription) {
		// TODO Auto-generated method stub
		initializeSouscriptionForModification(souscription);
		return souscriptionRepository.save(souscription);
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		souscriptionRepository.delete(id);
	}

	@Override
	public List<Souscription> findAll() {
		// TODO Auto-generated method stub
		return souscriptionRepository.findAll();
	}

	@Override
	public Page<Souscription> findAllByExample(Souscription souscription, Pageable pageable) {
		// TODO Auto-generated method stub
		ExampleMatcher matcher=ExampleMatcher.matching()
				.withMatcher("id", match -> match.exact())
				.withMatcher("address", match -> match.contains().ignoreCase())
				.withMatcher("cardNumberIdentifier", match -> match.contains().ignoreCase())
				.withMatcher("completeName", match -> match.contains().ignoreCase())
				.withMatcher("email", match -> match.contains().ignoreCase())
				.withMatcher("phoneNumber", match -> match.contains().ignoreCase())
				.withMatcher("account.id", match -> match.exact())
				.withIgnoreNullValues();
		
		Example<Souscription> example = Example.of(souscription, matcher);
		return souscriptionRepository.findAll(example, pageable);
	}

	@Override
	public Optional<Souscription> findSouscriptionById(Long id) {
		// TODO Auto-generated method stub
		return souscriptionRepository.findById(id);
	}
	
	private Souscription initializeSouscriptionForCreation(Souscription souscription){
		
		souscription.setCreationDate(utilityFactory.getDate());
		souscription.setModificationDate(utilityFactory.getDate());
		souscription.setPinCode(utilityFactory.generatePin().toString());
		return souscription;
	}
	
	private Souscription initializeSouscriptionForModification(Souscription souscription){		
		souscription.setModificationDate(utilityFactory.getDate());
		return souscription;
	}

}
