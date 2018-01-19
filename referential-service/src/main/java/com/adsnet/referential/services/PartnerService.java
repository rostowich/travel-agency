package com.adsnet.referential.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsnet.referential.entities.Partner;
import com.adsnet.referential.repositories.IPartnerRepository;


@Service
public class PartnerService implements IPartnerService {

	private static final Logger logger = LoggerFactory.getLogger(PartnerService.class);
	
	@Autowired
	private IPartnerRepository partnerRepository;

	@Override
	public Partner save(Partner partner) {
		// TODO Auto-generated method stub
		return partnerRepository.save(partner);
	}

	@Override
	public Optional<Partner> findById(Long id) {
		// TODO Auto-generated method stub
		return partnerRepository.findById(id);
	}

	@Override
	public List<Partner> findAll() {
		// TODO Auto-generated method stub
		return partnerRepository.findAll();
	}

	@Override
	public List<Partner> findAllCitiesContainingLabel(String label) {
		// TODO Auto-generated method stub
		return partnerRepository.findByLabelIgnoreCaseContaining(label);
	}

	@Override
	public Optional<Partner> ConnectAsPartner(String login, String password) {
		// TODO Auto-generated method stub
		return partnerRepository.findByLoginAndPassword(login, password);
	}

	
}
